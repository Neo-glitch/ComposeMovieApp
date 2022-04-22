package com.neo.composemovieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.neo.composemovieapp.model.Movie
import com.neo.composemovieapp.model.getMovies

// to store components


@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
//            .height(130.dp)
            .clickable {
                onItemClick.invoke(movie.id)
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
//                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
                Image(painter = rememberImagePainter(
                    data = movie.images[0],
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                ), contentDescription = "Movie Poster")
            }
            Column(modifier = Modifier.padding(4.dp))
            {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director : ${movie.title}", style = MaterialTheme.typography.caption)
                Text(text = "Released : ${movie.year}", style = MaterialTheme.typography.caption)

                // stuff to be shown when visible == true
                AnimatedVisibility(visible = expanded) {
                    Column {
                        // compose way of building composable
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 13.sp
                            )){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light
                            )){
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                    }
                }

                Icon(
                    imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }
        }

    }
}