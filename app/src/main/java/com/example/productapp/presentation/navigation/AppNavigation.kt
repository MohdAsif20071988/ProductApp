package com.example.productapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.productapp.domain.models.ProductAPIResponse
import com.example.productapp.presentation.components.AppProgressBar
import com.example.productapp.presentation.screens.DetailScreen
import com.example.productapp.presentation.screens.HomeScreen
import com.example.productapp.presentation.viewmodels.ProductDetailViewModel
import com.example.productapp.presentation.viewmodels.ProductListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, ScreenName.HOME_SCREEN.screenName) {
        composable(ScreenName.HOME_SCREEN.screenName) {
            val productListViewModel: ProductListViewModel = hiltViewModel()
            val productListState = productListViewModel.productListStateFlow.collectAsStateWithLifecycle().value
            when(productListState) {
                is ProductAPIResponse.LOADING -> AppProgressBar()
                is ProductAPIResponse.SUCCESS -> {
                    val listOfProduct = productListState.data!!
                    HomeScreen(listOfProduct) { productId ->
                        navController.navigate(ScreenName.DETAIL_SCREEN.screenName + "/$productId")
                    }
                }
                else -> {}
            }
        }

        composable(ScreenName.DETAIL_SCREEN.screenName + "/{id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) {
            val productDetailViewModel: ProductDetailViewModel = hiltViewModel()
            val productDetailState = productDetailViewModel.productDetailStateFlow.collectAsStateWithLifecycle().value
            when(productDetailState) {
                is ProductAPIResponse.LOADING -> AppProgressBar()
                is ProductAPIResponse.SUCCESS -> {
                    val product = productDetailState.data!!
                    DetailScreen(product) {
                        navController.popBackStack()
                    }
                }
                else -> {}
            }
        }
    }
}