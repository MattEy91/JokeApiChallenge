package de.eymers.matthias.jokerapichallenge.core

object Constant {
    private const val JOKE_CONTROLLER_URL = "joke/"
    private const val DEFAULT_QUERIES = "Any?lang=de&blacklistFlags=nsfw&type=twopart&amount=20"

    const val DESTINATION_JOKES_LIST = "jokeslist"
    const val BASE_URL = "https://v2.jokeapi.dev/"
    const val GET_SPECIFIC_JOKES_URL = JOKE_CONTROLLER_URL + DEFAULT_QUERIES
    const val GENERAL_API_ERROR_MESSAGE = "An random api error occurred"
    const val DEVICE_OFFLINE_ERROR_MESSAGE = "Your device has no connection"
    const val JOKES_REQUEST_AMOUNT_VALUE = 20
}