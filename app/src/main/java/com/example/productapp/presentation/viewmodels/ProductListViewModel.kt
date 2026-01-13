package com.example.productapp.presentation.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.domain.models.ProductAPIResponse
import com.example.productapp.domain.usecases.GetProductListUseCase
import com.example.productapp.presentation.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(val productListUseCase: GetProductListUseCase): ViewModel() {

    private val _productListStateFlow = MutableStateFlow<ProductAPIResponse<List<Product>>>(ProductAPIResponse.LOADING())
    val productListStateFlow: StateFlow<ProductAPIResponse<List<Product>>>
        get() = _productListStateFlow

    init {
        getProductList()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            productListUseCase().collect {
                _productListStateFlow.tryEmit(it)
            }
        }
    }
}