package ru.minnullin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import ru.minnullin.authorization.API_SHARED_FILE
import ru.minnullin.authorization.AUTHENTICATED_SHARED_KEY
import ru.minnullin.authorization.AuthentificateActivity
import ru.minnullin.authorization.Repository
import java.lang.Exception

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        with(postItems) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            callToNCMS()
        }
    }

    private fun callToNCMS(){
        postItems.visibility=View.GONE
        exceptionWindow.visibility=View.GONE
        progressBar.visibility=View.VISIBLE
        lifecycleScope.launch {
            try {
                val token=getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE).getString(
                    AUTHENTICATED_SHARED_KEY, ""
                )
                val list:List<PostDto> = Repository.getPosts(token) as List<PostDto>
                if(list.isNotEmpty()) {
                    postItems.adapter = token?.let { PostAdapter(list.toPostList(), it) }
                }else{
                    val intent = Intent(
                        App.INSTANCE,
                        AuthentificateActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
                progressBar.visibility = View.GONE
                postItems.visibility = View.VISIBLE
            } catch (e: Exception) {
                exceptionWindow.visibility=View.VISIBLE
                progressBar.visibility=View.GONE
                exceptionButton.setOnClickListener{
                    callToNCMS()
                }
            }

        }
    }
}

private fun List<PostDto>.toPostList(): List<Post> = map(PostDto::toPost)
