package ru.minnullin

import androidx.annotation.Nullable
import java.util.*

data class Post(
    val authorName: String,
    val authorDrawable: Int,
    val bodyText: String,
    val postDate: Date = Date(),
    val postType: PostType,
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
}