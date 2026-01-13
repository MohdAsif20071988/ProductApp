package com.example.productapp.utility

import com.example.productapp.domain.models.ProductDTO
import com.example.productapp.presentation.models.Product

fun ProductDTO.toProduct(): Product {
    return Product(id = this.id, title = this.title, thumbnail = this.thumbnail)
}