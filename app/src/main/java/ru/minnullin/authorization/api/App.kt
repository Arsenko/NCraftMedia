package ru.minnullin

import android.app.Application
import android.content.Context
import android.content.Intent
import ru.minnullin.authorization.AuthentificateActivity

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        INSTANCE = this
    }
}
fun App.toAuth(){
    val intent = Intent(
        App.INSTANCE,
        AuthentificateActivity::class.java
    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
}