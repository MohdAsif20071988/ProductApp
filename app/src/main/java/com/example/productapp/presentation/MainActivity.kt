package com.example.productapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.productapp.presentation.navigation.AppNavigation
import com.example.productapp.presentation.ui.theme.ProductAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppTheme {
               AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ProductAppTheme {
        AppNavigation()
    }
}