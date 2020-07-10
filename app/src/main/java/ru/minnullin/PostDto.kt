package ru.minnullin

import java.util.*

class PostDto(
    val id: Int, //
    val authorName: String, //
    val authorDrawable: Int,  //
    val bodyText: String,  //
    val postDate: Date = Date(), //
    val repostPost: Post?,  //
    val postType: PostType,  //
    var dislikeCounter: Int,  //
    var dislikedByMe: Boolean = false,  //
    var likeCounter: Int,  //
    var likedByMe: Boolean = false,  //
    var commentCounter: Int,  //
    var shareCounter: Int,  //
    val location: Pair<Double, Double>?,  //
    val link: String?,  //
    var postImage: Int?  //
) {
    fun toPost()=
        Post(
            this.id,
            this.authorName,
            this.authorDrawable,
            this.bodyText,
            this.postDate,
            this.repostPost,
            this.postType,
            this.dislikeCounter,
            this.dislikedByMe,
            this.likeCounter,
            this.likedByMe,
            this.commentCounter,
            this.shareCounter,
            this.location,
            this.link,
            this.postImage
        )
    companion object {
        fun generateComp(model: Post) = PostDto(
            id = model.id,
            authorName = model.authorName,
            authorDrawable = model.authorDrawable,
            bodyText = model.bodyText,
            postDate = model.postDate,
            repostPost = model.repostPost,
            postType = model.postType,
            dislikeCounter = model.dislikeCounter,
            dislikedByMe = model.dislikedByMe,
            likeCounter = model.likeCounter,
            likedByMe = model.likedByMe,
            commentCounter = model.commentCounter,
            shareCounter = model.shareCounter,
            location = model.location,
            link = model.link,
            postImage = model.postImage
        )
        fun format(input:List<PostDto>):List<Post>{
            return emptyList()
        }
    }


}