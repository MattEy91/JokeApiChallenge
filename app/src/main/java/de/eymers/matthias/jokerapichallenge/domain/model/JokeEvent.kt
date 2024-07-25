package de.eymers.matthias.jokerapichallenge.domain.model

sealed class JokeEvent {
    data object Loading: JokeEvent()
    data class Success(val jokes: List<Joke>): JokeEvent()
    data class Error(val cause: Throwable): JokeEvent()
}