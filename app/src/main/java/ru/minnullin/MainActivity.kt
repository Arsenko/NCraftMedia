package ru.minnullin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        var profileStatus:Boolean=false
        val testPost=Post("netlogy",R.drawable.ic_health,"First post in our network!", Date(),0,8,2)
        commitView(testPost)
        likeButton.setOnClickListener(View.OnClickListener {
            profileStatus = if(!profileStatus){
                likeButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_24dp))
                testPost.likeIncrease()
                commitView(testPost)
                true
            }else{
                likeButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_grey_24dp))
                testPost.likeDecrease()
                commitView(testPost)
                false
            }
        })
    }

    private fun commitView(post:Post){
        authorIcon.setImageDrawable(getDrawable(post.drawable))
        authorName.text=post.author
        postBody.text=post.text
        val format=SimpleDateFormat("dd/MM/yyyy")
        postDate.text=format.format(post.date)
        likeNumber.text=if(post.like<=0){
            ""
        }else{
            post.like.toString()
        }
        commentNumber.text=post.comment.toString()
        shareNumber.text=post.share.toString()
    }
}
