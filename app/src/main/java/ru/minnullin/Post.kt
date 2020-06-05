package ru.minnullin

import androidx.annotation.Nullable
import java.util.*

data class Post(
    val id: Int,
    val authorName: String,
    val authorDrawable: Int,
    val bodyText: String,
    val postDate: Date = Date(),
    val repostPost: Post?,
    val postType: PostType,
    var dislikeCounter: Int,
    var dislikedByMe: Boolean = false,
    var likeCounter: Int,
    var likedByMe: Boolean = false,
    var commentCounter: Int,
    var shareCounter: Int,
    val location: Pair<Double, Double>?,
    val link: String?,
    var postImage: Int?

) {
    fun likeIncrease() {
        likeCounter = likeCounter.inc()
    }

    fun likeDecrease() {
        likeCounter = likeCounter.dec()
    }

    fun commentIncrease() {
        commentCounter = commentCounter.inc()
    }

    fun shareIncrease() {
        shareCounter = shareCounter.inc()
    }

    fun dislikeIncrease(){
        dislikeCounter = dislikeCounter.inc()
    }

    fun dislikeDecrease(){
        dislikeCounter = dislikeCounter.dec()
    }
}