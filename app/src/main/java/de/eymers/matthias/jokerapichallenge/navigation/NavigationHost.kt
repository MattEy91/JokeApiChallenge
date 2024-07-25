package de.eymers.matthias.jokerapichallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.eymers.matthias.jokerapichallenge.core.Constant
import de.eymers.matthias.jokerapichallenge.ui.list.JokesListScreen

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Constant.DESTINATION_JOKES_LIST
    ) {
        composable(Destination.JokesList.route) { JokesListScreen() }
    }
}