package de.eymers.matthias.jokerapichallenge.data.repository

import de.eymers.matthias.jokerapichallenge.core.Constant.GENERAL_API_ERROR_MESSAGE
import de.eymers.matthias.jokerapichallenge.core.Constant.JOKES_REQUEST_AMOUNT_VALUE
import de.eymers.matthias.jokerapichallenge.data.remote.JokeApi
import de.eymers.matthias.jokerapichallenge.data.remote.throwable.GeneralApiException
import de.eymers.matthias.jokerapichallenge.domain.model.Joke
import de.eymers.matthias.jokerapichallenge.domain.model.JokeEvent
import de.eymers.matthias.jokerapichallenge.domain.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class JokeRepositoryImpl(
    private val api: JokeApi
) : JokeRepository {

    /*Since the API can send us a maximum of 10 jokes per request,
    and it is not always guaranteed that 10 jokes will be delivered each time,
    I repeat the request until we have reached 20 items.*/
    override suspend fun getTwentyJokesRemote(): Flow<JokeEvent> = flow {
        emit(JokeEvent.Loading)

        val twentyJokes = mutableSetOf<Joke>()

        while (twentyJokes.size < JOKES_REQUEST_AMOUNT_VALUE) {
            val jokesResponse = api.getJokesAsync()

            if (jokesResponse.error) {
                emit(JokeEvent.Error(cause = GeneralApiException(errorMessage = GENERAL_API_ERROR_MESSAGE)))

                return@flow
            }

            val jokes = jokesResponse.jokes.map { it.toJoke() }

            twentyJokes.addAll(jokes)
        }

        emit(JokeEvent.Success(jokes = twentyJokes.toList().sortedBy { it.id }))

    }.catch { e ->
        emit(JokeEvent.Error(cause = e))
    }.flowOn(Dispatchers.IO)
}