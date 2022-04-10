package com.neo.composemovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neo.composemovieapp.screens.home.HomeScreen
import com.neo.composemovieapp.screens.details.DetailsScreen


/**
 * To create the navController, navHost and navGraph(auto created when start destination is set)
 */
@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name){
        // for building nav graph

        composable(route = MovieScreens.HomeScreen.name){
            // we pass where this route should lead to
            HomeScreen(navController = navController)
        }

        composable(route = MovieScreens.DetailScreen.name+"/{movie}",
            // specify type of arg(movie) is of string type
        arguments = listOf(navArgument(name = "movie"){type= NavType.StringType})){
            backStackEntry ->
            DetailsScreen(navController, backStackEntry.arguments?.getString("movie"))
        }
    }
}