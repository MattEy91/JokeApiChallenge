package de.eymers.matthias.jokerapichallenge.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import de.eymers.matthias.jokerapichallenge.core.isOnline
import de.eymers.matthias.jokerapichallenge.domain.model.Joke
import de.eymers.matthias.jokerapichallenge.domain.model.JokeEvent
import de.eymers.matthias.jokerapichallenge.ui.composable.AppDialog
import de.eymers.matthias.jokerapichallenge.ui.composable.GeneralErrorView

@Composable
fun JokesListScreen(
    viewModel: JokesListViewModel = hiltViewModel()
) {
    val jokeEvent by viewModel.jokeEvent.collectAsState(initial = null)
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var clickedJoke by remember { mutableStateOf<Joke?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchJokes(isOnline = context.isOnline())
    }

    JokesListContent(
        jokeEvent = jokeEvent,
        onJokeClick = {
            clickedJoke = it
            showDialog = true
        }
    )
    
    if (showDialog) {
        AppDialog(message = clickedJoke?.delivery!!) {
            showDialog = false
        }
    }
}

@Composable
fun JokesListContent(
    jokeEvent: JokeEvent? = null,
    onJokeClick: (Joke) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (jokeEvent) {
                is JokeEvent.Error -> GeneralErrorView(errorMessage = jokeEvent.cause.message)
                is JokeEvent.Loading -> CircularProgressIndicator()
                is JokeEvent.Success -> JokesListLazyColumn(
                    jokes = jokeEvent.jokes
                ) {
                    onJokeClick(it)
                }
                null -> Unit
            }
        }
    }
}



@PreviewLightDark
@PreviewDynamicColors
@PreviewFontScale
@Preview(showBackground = true)
@Composable
fun JokesListScreenPreview() {
    JokesListContent()
}