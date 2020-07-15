package ru.minnullin.authorization.api

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.minnullin.App
import ru.minnullin.authorization.API_SHARED_FILE
import ru.minnullin.authorization.AUTHENTICATED_SHARED_KEY
import ru.minnullin.authorization.AuthentificateActivity
import ru.minnullin.authorization.Repository

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code()) {
            400 -> {
                //TODO
            }
            401 -> {
                App.context.getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE)
                    .edit()
                    .putString(AUTHENTICATED_SHARED_KEY,"")
                    .commit()
            }
        }
        return response
    }

}

/*class ActivityToLaunch:Activity(){
    fun error401(){
    val intent = Intent(
            App.INSTANCE,
            AuthentificateActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE)
            .edit()
            .putString(AUTHENTICATED_SHARED_KEY,"")
            .commit()
    }
}*/



