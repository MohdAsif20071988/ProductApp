package com.example.productapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.productapp.presentation.models.Product

@Composable
fun ProductItem(product: Product, iconSize: Int, onItemClicked: (productId: Int) -> Unit = {}) {
        Card(onClick = { onItemClicked(product.id) }, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Cyan)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(modifier = Modifier.size(iconSize.dp), model = product.thumbnail, contentDescription = "", contentScale = ContentScale.Crop)
                Text(
                    text = product.title,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ProductItem(
        Product(1, "Product", ""), 100,
        onItemClicked = { }
    )
}