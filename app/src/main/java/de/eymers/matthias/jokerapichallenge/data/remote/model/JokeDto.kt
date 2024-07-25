package de.eymers.matthias.jokerapichallenge.data.remote.model

import de.eymers.matthias.jokerapichallenge.domain.model.Joke

data class JokeDto (
    val category: Category,
    val type: Type,
    val setup: String,
    val delivery: String,
    val flags: Flags,
    val id: Long,
    val safe: Boolean,
    val lang: Lang
)

{
    fun toJoke(): Joke {
        return Joke(
            id = id,
            setup = setup,
            delivery = delivery
        )
    }
}

enum class Category {
    Christmas,
    Programming,
    Pun,
    Dark,
    Misc,
    Spooky
}

data class Flags (
    val nsfw: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val explicit: Boolean
)

enum class Lang {
    En,
    De,
    Es,
    Fr,
    Pt,
    Cs
}

enum class Type {
    Twopart,
    Single
}