package de.eymers.matthias.jokerapichallenge.data.remote.throwable

data class GeneralApiException(
    val errorMessage: String
) : Exception() {
    override val message: String
        get() = errorMessage
}