package de.eymers.matthias.jokerapichallenge.domain.repository

import de.eymers.matthias.jokerapichallenge.domain.model.JokeEvent
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    suspend fun getTwentyJokesRemote(): Flow<JokeEvent>
}