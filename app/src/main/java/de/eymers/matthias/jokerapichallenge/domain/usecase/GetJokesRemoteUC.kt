package de.eymers.matthias.jokerapichallenge.domain.usecase

import de.eymers.matthias.jokerapichallenge.domain.repository.JokeRepository
import javax.inject.Inject

class GetJokesRemoteUC @Inject constructor(
    private val repo: JokeRepository
) {
    suspend operator fun invoke() = repo.getTwentyJokesRemote()
}