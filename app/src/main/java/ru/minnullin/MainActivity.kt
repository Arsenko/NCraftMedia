package ru.minnullin

import android.content.Context
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
        launch {
            val list =  Repository.getPosts( getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE).getString(
                AUTHENTICATED_SHARED_KEY, ""
            ))
            try {
                if(list.isNotEmpty()) {
                    postItems.adapter = PostAdapter(list as List<Post>)
                }
                Log.d("aaa",list.toString())
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
