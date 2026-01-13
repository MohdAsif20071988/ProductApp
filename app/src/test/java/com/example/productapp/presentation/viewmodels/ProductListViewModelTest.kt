package com.example.productapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.productapp.MainTestDispatcher
import com.example.productapp.domain.models.ProductAPIResponse
import com.example.productapp.domain.usecases.GetProductListUseCase
import com.example.productapp.presentation.models.Product
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductListViewModelTest {
    @get:Rule
    private val mainTestDispatcher = MainTestDispatcher()

    @get:Rule
    private val intentTaskExecutorRule = InstantTaskExecutorRule()

    private val getProductListUseCase: GetProductListUseCase = mockk()

    @Before
    fun setUp() {}

    @Test
    fun get_productList() = runTest {
        val loadingResponse = ProductAPIResponse.LOADING<List<Product>>()
        val successResponse = ProductAPIResponse.SUCCESS<List<Product>>((emptyList()))

        coEvery { getProductListUseCase() } returns
            flow {
                emit(loadingResponse)
                delay(100)
                emit(successResponse)
            }
        val sut = ProductListViewModel(getProductListUseCase)
        sut.productListStateFlow.test {
            assertTrue(awaitItem() == loadingResponse)
            sut.getProductList()

            val successData = awaitItem() as ProductAPIResponse.SUCCESS<List<Product>>
            assertTrue(successData == successResponse)
            assertTrue(successData.data?.isEmpty() == true)
            cancel()
        }
    }

    @Test
    fun get_productList_Failed() = runTest {
        val loadingResponse = ProductAPIResponse.LOADING<List<Product>>()
        val errorResponse = ProductAPIResponse.ERROR<List<Product>>(Exception("Something Went Wrong. Please try again."))

        coEvery { getProductListUseCase() } returns
                flow {
                    emit(loadingResponse)
                    delay(100)
                    emit(errorResponse)
                }

        val sut = ProductListViewModel(getProductListUseCase)
        sut.productListStateFlow.test {
            assertTrue(awaitItem() == loadingResponse)
            sut.getProductList()

            val errorData = awaitItem() as ProductAPIResponse.ERROR<List<Product>>
            assertTrue(errorData == errorResponse)
            assertTrue(errorData.error.message == "Something Went Wrong. Please try again.")
            cancel()
            clearAllMocks()
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}