package ru.minnullin

import java.util.*

data class Post (
    val authorName:String,
    val authorDrawable:Int,
    val bodyText:String,
    val postDate:Date=Date(),
    var likeCounter:Int,
    var commentCounter:Int,
    var shareCounter:Int
){
    fun likeIncrease():Post =
        copy(likeCounter=likeCounter.inc())

    fun likeDecrease():Post =
        copy(likeCounter=likeCounter.dec())

    fun commentIncrease():Post =
        copy(commentCounter=commentCounter.inc())

    fun shareIncrease():Post =
        copy(shareCounter=shareCounter.inc())
}
