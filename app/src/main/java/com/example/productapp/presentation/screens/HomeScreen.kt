package com.example.productapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productapp.domain.models.ProductAPIResponse
import com.example.productapp.presentation.components.ProductItem
import com.example.productapp.presentation.components.Toolbar
import com.example.productapp.presentation.models.Product
import com.example.productapp.presentation.navigation.ScreenName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    productList: List<Product>,
    onItemClicked: (productId: Int) -> Unit = {}
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(ScreenName.HOME_SCREEN.screenName, isBackNavigationEnabled = false) { }
        },
    )
    { innerPadding ->
        LazyColumn(Modifier.padding(innerPadding)) {
            items(productList) {
                ProductItem(it, 100, onItemClicked = onItemClicked)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(listOf(
                    Product(1, "Image 1", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"),
                    Product(2, "Image 2", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"),
                    Product(3, "Image 3", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"),
                    Product(4, "Image 4", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"),
                    Product(5, "Image 5", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp")
        )
    )
}