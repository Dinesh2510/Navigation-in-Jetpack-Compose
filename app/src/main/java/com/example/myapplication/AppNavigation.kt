package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainPage" ){


        composable("mainPage"){
            MainPage(navController = navController)
        }

        composable("detailsPage"){
            DetailsPage(navController = navController)

        }

        composable("search"){
            SearchPage(navController = navController)
        }


    }


}