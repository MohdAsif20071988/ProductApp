package com.example.productapp.presentation.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.domain.models.ProductAPIResponse
import com.example.productapp.domain.usecases.GetProductDetailUseCase
import com.example.productapp.presentation.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(val productDetailUseCase: GetProductDetailUseCase, val savedStateHandle: SavedStateHandle): ViewModel() {

    private var _productDetailStateFlow = MutableStateFlow<ProductAPIResponse<Product>>(ProductAPIResponse.LOADING())
    val productDetailStateFlow: StateFlow<ProductAPIResponse<Product>>
        get() = _productDetailStateFlow

    init {
        getProductDetail(savedStateHandle.get<Int>("id").toString())
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getProductDetail(id: String) {
        viewModelScope.launch {
            productDetailUseCase(id).collect {
                _productDetailStateFlow.value = it
            }
        }
    }
}