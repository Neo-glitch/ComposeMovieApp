package com.neo.composemovieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neo.composemovieapp.navigation.MovieNavigation
import com.neo.composemovieapp.ui.theme.ComposeMovieAppTheme
import java.nio.file.Files.size

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeMovieAppTheme() {
        content()
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
                        Log.d("TAG", "Movie : $movie")

                    }
                }
            }
        }
    }
}



@Composable
fun MovieRow(movie: String, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemClick.invoke(movie)
            },
        shape = CircleShape.copy(all = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 5.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
            }
            Text(text = movie)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}