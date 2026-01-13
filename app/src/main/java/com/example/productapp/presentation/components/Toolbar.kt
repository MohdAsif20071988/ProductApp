package com.example.productapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title: String, isBackNavigationEnabled: Boolean, onBackNavigation: () -> Unit) {
    TopAppBar(modifier = Modifier.fillMaxWidth(),
        title = {
            Text(modifier = Modifier.fillMaxWidth(),
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },
        navigationIcon = {
            if (isBackNavigationEnabled) {
                IconButton(onClick = onBackNavigation) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.Black)
                }
            }
        },
        actions = {
            if (isBackNavigationEnabled) {
                Spacer(modifier = Modifier.size(30.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray)
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ToolbarPreview() {
    Toolbar("Home", true){}
}