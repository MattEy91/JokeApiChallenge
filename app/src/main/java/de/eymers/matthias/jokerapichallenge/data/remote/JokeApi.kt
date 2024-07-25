package de.eymers.matthias.jokerapichallenge.data.remote

import de.eymers.matthias.jokerapichallenge.core.Constant.GET_SPECIFIC_JOKES_URL
import de.eymers.matthias.jokerapichallenge.data.remote.model.JokeResponse
import retrofit2.http.GET

interface JokeApi {

    @GET(GET_SPECIFIC_JOKES_URL)
    suspend fun getJokesAsync(): JokeResponse
}