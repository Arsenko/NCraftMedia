package ru.minnullin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonArray
import io.ktor.client.HttpClient
import io.ktor.client.call.Type
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.util.KtorExperimentalAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*

class MainActivity : AppCompatActivity() {
    private var list: List<Post?>?=null

    @KtorExperimentalAPI
    val client = HttpClient {
        install(JsonFeature) {
            acceptContentTypes = listOf(
                ContentType.Text.Plain,
                ContentType.Application.Json
            )
            serializer = GsonSerializer()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        progressBar.visibility = View.VISIBLE
        post_items.visibility = View.GONE
        with(post_items) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            CoroutineScope(IO).launch {
                changeView()
            }.invokeOnCompletion {
                post_items.adapter=PostAdapter(list as List<Post>)
            }

        }
    }

    private suspend fun changeView() { //Меняет видимость
        withContext(Dispatchers.Main) {
            if (!list.isNullOrEmpty()) {
                progressBar.visibility = View.VISIBLE
                post_items.visibility = View.GONE
            } else {
                list=getPosts().toList()
                progressBar.visibility = View.GONE
                post_items.visibility = View.VISIBLE
            }
        }
    }

    @KtorExperimentalAPI
    private suspend fun getPosts(): Array<Post?> { //эмулирует задержку при получении данных
        var stringFromClient: JsonArray =
            client.get("https://raw.githubusercontent.com/Arsenko/NCMS/master/posts.json")
        var listJson = stringFromClient.toList()
        val result = arrayOfNulls<Post>(listJson.size)
        for (i in listJson.indices) {
            result[i] = Gson().fromJson(listJson[i], Post::class.java)
        }
        return result
    }
}
