package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow


@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "MyMovieApp") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = MovieScreens.FavScreen.name) }) {
                            Row{
                                Icon(imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier.padding(4.dp))
                                Text(text = "My Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp))
                            }

                        }
                    }

                }
            )
        }
    ) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies()){
    LazyColumn {
        // item { Text(text = "Header")}    // add a single composable to LazyColumn

        items(items = movies) { movie ->     // add a list of composables to LazyColumn
            MovieRow(movie = movie) { movieId ->  // render MoviewRow composable for each item
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
            }
        }
        /*
        itemsIndexed(movies){ index, movie -> // add a list of composables with index
        }
        */


    }
}
