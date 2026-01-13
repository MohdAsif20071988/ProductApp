package com.example.productapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productapp.presentation.components.ProductItem
import com.example.productapp.presentation.components.Toolbar
import com.example.productapp.presentation.models.Product
import com.example.productapp.presentation.navigation.ScreenName

@Composable
fun DetailScreen(product: Product, onBackNavigation: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(ScreenName.DETAIL_SCREEN.screenName, isBackNavigationEnabled = true, onBackNavigation)
        },
    )
    { innerPadding ->
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            ProductItem(product, 200){}
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun detailScreenPreview() {
    DetailScreen(Product(1, "Image 1", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp")){}
}