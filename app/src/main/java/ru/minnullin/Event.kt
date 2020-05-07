package ru.minnullin

import java.util.*

data class Event(
    val authorName:String,
    val authorDrawable:Int,
    val bodyText:String,
    val location: Pair<Double,Double>,
    val postDate: Date = Date(),
    var likeCounter:Int,
    var commentCounter:Int,
    var shareCounter:Int
) {
    fun likeIncrease(): Event =
        copy(likeCounter=likeCounter.inc())

    fun likeDecrease(): Event =
        copy(likeCounter=likeCounter.dec())

    fun commentIncrease(): Event =
        copy(commentCounter=commentCounter.inc())

    fun shareIncrease(): Event =
        copy(shareCounter=shareCounter.inc())

}