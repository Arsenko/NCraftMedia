package ru.minnullin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.ktor.client.HttpClient
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonArray
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.util.KtorExperimentalAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    @KtorExperimentalAPI
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

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
                val list =
                    client.get<List<Post>>("https://srv-ncms.herokuapp.com/api/v1/posts")
                postItems.adapter = PostAdapter(list)
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
