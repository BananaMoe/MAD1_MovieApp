package com.example.movieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {}) {
    var showDetails by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .heightIn(130.dp, 220.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier
                .size(100.dp)
                .padding(12.dp)
            ) {
                // coil_version = "1.4.0"
                // Icon(imageVector = Icons.Default.AccountBox, contentDescription = "moviepic")
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie poster"
                )

                // coil_version = "2.0.0-rc02"
                /*AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie image",
                )*/
            }
            Column() {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}")
                Text(text = "Year: ${movie.year}")
                IconButton(onClick = { showDetails = !showDetails }) {
                    Icon(
                        Icons.Default.run {
                            if (showDetails)
                                KeyboardArrowDown
                            else
                                KeyboardArrowUp
                        },
                        contentDescription = "",
                    )
                }
                if(showDetails){
                    Row{
                        Text(text = "Plot: ${movie.plot}")
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "")
            }

        }
    }
}