package com.example.productapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.productapp.MainTestDispatcher
import com.example.productapp.domain.models.ProductAPIResponse
import com.example.productapp.domain.usecases.GetProductDetailUseCase
import com.example.productapp.presentation.models.Product
import io.mockk.clearAllMocks
import io.mockk.clearMocks
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

class ProductDetailViewModelTest {
    @get:Rule
    private val mainTestDispatcher = MainTestDispatcher()

    @get:Rule
    private val intentTaskExecutorRule = InstantTaskExecutorRule()

    private val getProductDetailUseCase: GetProductDetailUseCase = mockk()

    @Before
    fun setUp() {}

    @Test
    fun get_productDetail_Success() = runTest {
        val loadingResponse = ProductAPIResponse.LOADING<Product>()
        val successResponse = ProductAPIResponse.SUCCESS<Product>(Product(1, "", ""))

        coEvery { getProductDetailUseCase("1") } returns
                flow {
                    emit(loadingResponse)
                    delay(100)
                    emit(successResponse)
                }
        val savedStateHandle = SavedStateHandle()
        savedStateHandle.set("id", "1")
        val sut = ProductDetailViewModel(getProductDetailUseCase, savedStateHandle)
        sut.productDetailStateFlow.test {
            assertTrue(awaitItem() == loadingResponse)
            sut.getProductDetail("1")

            val successData = awaitItem() as ProductAPIResponse.SUCCESS<Product>
            assertTrue(successData == successResponse)
            assertTrue(successData.data?.id.toString() == "1")
            cancel()
        }
    }

    @Test
    fun get_productDetail_Failed() = runTest {
        val loadingResponse = ProductAPIResponse.LOADING<Product>()
        val errorResponse = ProductAPIResponse.ERROR<Product>(Exception("Something Went Wrong. Please try again."))

        coEvery { getProductDetailUseCase("1") } returns
                flow {
                    emit(loadingResponse)
                    delay(100)
                    emit(errorResponse)
                }
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["id"] = "1"
        val sut = ProductDetailViewModel(getProductDetailUseCase, savedStateHandle)
        sut.productDetailStateFlow.test {
            assertTrue(awaitItem() == loadingResponse)
            sut.getProductDetail("1")

            val errorData = awaitItem() as ProductAPIResponse.ERROR<Product>
            assertTrue(errorData == errorResponse)
            assertTrue(errorData.error.message == "Something Went Wrong. Please try again.")
            cancel()
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}