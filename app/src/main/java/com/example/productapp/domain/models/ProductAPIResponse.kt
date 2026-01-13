package com.example.productapp.domain.models

sealed class ProductAPIResponse<T>(data: T? = null, errorData: Exception? = null) {
    class LOADING<T>(): ProductAPIResponse<T> ()
    data class SUCCESS<T> (val data: T?): ProductAPIResponse<T>()
    data class ERROR<T> (val error: Exception): ProductAPIResponse<T>()
}