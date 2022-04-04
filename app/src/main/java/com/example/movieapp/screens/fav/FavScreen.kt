package com.example.movieapp.screens.fav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@Composable
fun FavScreen(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.navigate(route = MovieScreens.HomeScreen.name) // go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "My Favorite Movies")
                }
            }
        }
    ) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies()){
    LazyColumn {
        // item { Text(text = "Header")}    // add a single composable to LazyColumn

        var favRate = false
        items(items = movies) { movie ->     // add a list of composables to LazyColumn
            if(favRate) {
                MovieRow(movie = movie) { movieId ->  // render MoviewRow composable for each item
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                }
            }
            favRate = !favRate
        }
        /*
        itemsIndexed(movies){ index, movie -> // add a list of composables with index
        }
        */


    }
}