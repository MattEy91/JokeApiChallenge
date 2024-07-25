package de.eymers.matthias.jokerapichallenge.navigation

import de.eymers.matthias.jokerapichallenge.core.Constant.DESTINATION_JOKES_LIST

sealed class Destination(val route: String) {
    data object JokesList: Destination(DESTINATION_JOKES_LIST)
}