package de.eymers.matthias.jokerapichallenge.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import de.eymers.matthias.jokerapichallenge.domain.model.Joke
import de.eymers.matthias.jokerapichallenge.ui.theme.JokerApiChallengeTheme

@Composable
fun JokesListLazyColumn(
    jokes: List<Joke>,
    onJokeClick: (Joke) -> Unit = {}
) {
    JokerApiChallengeTheme {
        if (jokes.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {
                itemsIndexed(
                    items = jokes,
                    key = { _, newsEntry -> newsEntry.id },
                    itemContent = { _, element ->
                        JokeListItem(joke = element) {
                            onJokeClick(it)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun JokeListItem(
    joke: Joke,
    onJokeClick: (Joke) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onJokeClick(joke) }
            .padding(24.dp)
    ) {
        Text(
            text = joke.id.toString()
        )
        Text(
            text = joke.setup
        )
    }
}

@PreviewLightDark
@PreviewDynamicColors
@PreviewFontScale
@Preview(showBackground = true)
@Composable
fun GeneralErrorPreview() {
    JokesListLazyColumn(
        jokes = listOf(
            Joke(
                id = 0,
                setup = "knock knock",
                delivery = "whos there?"
            )
        )
    )
}