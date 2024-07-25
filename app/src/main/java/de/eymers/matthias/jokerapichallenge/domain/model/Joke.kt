package de.eymers.matthias.jokerapichallenge.domain.model

data class Joke (
    val id: Long,
    val setup: String,
    val delivery: String
)