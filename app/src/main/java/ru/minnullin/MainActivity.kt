package ru.minnullin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        progressBar.visibility = View.VISIBLE
        post_items.visibility = View.GONE
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
        //progressBar.max=list.size

        with(post_items) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
            CoroutineScope(IO).launch {
                fromMain()
            }
        }


    }

    private suspend fun changeView(value:Boolean) { //Меняет видимость
        withContext(Dispatchers.Main) {
            if (value) {
                progressBar.visibility = View.GONE
                post_items.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.VISIBLE
                post_items.visibility = View.GONE
            }
        }
    }

    private suspend fun getPosts(): Boolean { //эмулирует задержку при получении данных
        delay(10000)
        return true
    }

    private suspend fun fromMain() { //Вызывает функцию из Main потока
        CoroutineScope(IO).launch {
            val result=getPosts()
            changeView(result)
        }

    }
}
