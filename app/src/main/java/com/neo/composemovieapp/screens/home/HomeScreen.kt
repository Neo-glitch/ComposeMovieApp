package com.neo.composemovieapp.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neo.composemovieapp.MovieRow
import com.neo.composemovieapp.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Text(text = "Movies")
        }
    }) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<String> =
        listOf(
            "Avengers", "SpiderMan", "300", "Power book IV",
            "Peace maker", "Top Boy", "Gangs of London",
            "007"
        )
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(12.dp)) {
            LazyColumn {
                items(movieList) {
                    MovieRow(movie = it) { movie ->
                        // pass the arg
                        navController.navigate(route = MovieScreens.DetailScreen.name+"/$movie")
                    }
                }
            }
        }
    }
}