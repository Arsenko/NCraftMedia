package ru.minnullin

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import java.text.SimpleDateFormat

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(adapter: PostAdapter, position: Int, post: Post) {
        with(itemView) {
            val format = SimpleDateFormat("dd/MM/yyyy")
            when (post.postType) {
                PostType.Post -> {
                    locationButton.isVisible = false
                    postImage.isVisible = false
                }
                PostType.Event -> {
                    locationButton.setOnClickListener {
                        val geo = Intent().apply {
                            data = Uri.parse("geo:${post.location?.first}${post.location?.second}")
                        }
                        context.startActivity(geo)
                    }
                    locationButton.isVisible = true
                    postImage.isVisible = false

                }
                PostType.Video -> {
                    postImage.setOnClickListener {
                        val watchVideo = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(post.link)
                        }
                        context.startActivity(watchVideo)
                    }
                    locationButton.isVisible = false
                    postImage.isVisible = true

                }
                PostType.Advertising -> {
                    postImage.setImageDrawable(post.postImage?.let { getDrawable(context, it) })
                    postImage.setOnClickListener {
                        val watchVideo = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(post.link)
                        }
                        context.startActivity(watchVideo)
                    }
                    locationButton.isVisible = false
                    postImage.isVisible = true
                }
            }
            commentNumber.text = post.commentCounter.toString()
            shareNumber.text = post.shareCounter.toString()
            authorIcon.setImageDrawable(getDrawable(context, post.authorDrawable))
            authorName.text = post.authorName
            postBody.text = post.bodyText
            postDate.text = format.format(post.postDate)
            likeNumber.text = if (post.likeCounter <= 0) {
                ""
            } else {
                post.likeCounter.toString()
            }
            if (post.likedByMe) {
                likeButton.setImageDrawable(getDrawable(context, R.drawable.ic_favorite_red_24dp))
            } else {
                likeButton.setImageDrawable(getDrawable(context, R.drawable.ic_favorite_grey_24dp))
            }
            likeButton.setOnClickListener {
                post.likedByMe = if (!post.likedByMe) {
                    likeButton.setImageDrawable(
                        getDrawable(
                            context,
                            R.drawable.ic_favorite_red_24dp
                        )
                    )
                    post.likeIncrease()
                    adapter.notifyItemChanged(position)
                    true
                } else {
                    likeButton.setImageDrawable(
                        getDrawable(
                            context,
                            R.drawable.ic_favorite_grey_24dp
                        )
                    )
                    post.likeDecrease()
                    adapter.notifyItemChanged(position)
                    false
                }
            }
            shareButton.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT, """${post.authorName} (${post.postDate})
                                    ${post.bodyText}""".trimIndent()
                    )
                    type = "text/plain"
                }
                post.shareIncrease()
                context.startActivity(intent)
            }
        }
    }
}
