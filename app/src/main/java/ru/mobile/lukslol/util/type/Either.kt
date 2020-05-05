package ru.mobile.lukslol.util.type

sealed class Either<out L, out R> {

    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    fun <C> fold(ifLeft: (L) -> C, ifRight: (R) -> C): C =
        when (this) {
            is Left -> ifLeft(value)
            is Right -> ifRight(value)
        }
}

fun <T> T.left() = Either.Left(this)
fun <T> T.right() = Either.Right(this)