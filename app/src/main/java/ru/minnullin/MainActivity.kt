package ru.minnullin

import android.content.Intent
import android.location.Location
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val list = listOf(
            Post(
                "netlogy",
                R.drawable.ic_health,
                "First post in our network!",
                Date(),
                PostType.Post,
                0,
                false,
                8,
                2,
                null,
                null,
                null
            ),
            Post(
                "etlogy",
                R.drawable.ic_health,
                "Second post in our network!",
                Date(),
                PostType.Event,
                0,
                false,
                8,
                2,
                Pair(60.0, 85.0),
                null,
                null
            ),
            Post(
                "tlogy",
                R.drawable.ic_health,
                "Third post in our network!",
                Date(),
                PostType.Video,
                0,
                false,
                8,
                2,
                null,
                "https://www.youtube.com/watch?v=lO5_E9aObE0",
                null
            ),
            Post(
                "logy",
                R.drawable.ic_health,
                "Fourth post in our network!",
                Date(),
                PostType.Advertising,
                0,
                false,
                8,
                2,
                null,
                "https://l.netology.ru/marketing_director_guide?utm_source=vk&utm_medium=smm&utm_campaign=bim_md_oz_vk_smm_guide&utm_content=12052020",
                R.mipmap.ad_foreground
            )
        )

        with(post_items) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
        }
        /*commitView(testPost)
        likeButton.setOnClickListener {
            profileStatus = if (!profileStatus) {
                likeButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_24dp))
                testPost.likeIncrease()
                commitView(testPost)
                true
            } else {
                likeButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_grey_24dp))
                testPost.likeDecrease()
                commitView(testPost)
                false
            }
    }*/
    }
}

/*
}*/
