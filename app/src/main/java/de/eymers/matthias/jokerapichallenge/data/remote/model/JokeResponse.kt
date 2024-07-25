package de.eymers.matthias.jokerapichallenge.data.remote.model

data class JokeResponse (
    val error: Boolean,
    val amount: Long,
    val jokes: List<JokeDto>
)