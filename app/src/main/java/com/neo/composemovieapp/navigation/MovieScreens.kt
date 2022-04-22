package com.neo.composemovieapp.navigation

import java.lang.IllegalArgumentException


// enum to list all possible screens on our app( to organise things a lil)
enum class MovieScreens {
    HomeScreen,
    DetailScreen;

    companion object {
        fun fromRoute(route: String?): MovieScreens {
            return when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not found")
            }
        }
    }
}