package ru.minnullin.authorization

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.minnullin.authorization.api.API
import ru.minnullin.authorization.api.AuthRequestParams
import ru.minnullin.authorization.api.RegistrationRequestParams


object Repository {

    // Ленивое создание Retrofit экземпляра
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://srv-ncms.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Ленивое создание API
    private val API: API by lazy {
        retrofit.create(ru.minnullin.authorization.api.API::class.java)
    }

    suspend fun authenticate(login: String, password: String) = API.authenticate(
        AuthRequestParams(login, password)
    )

    suspend fun register(login: String, password: String) =
        API.register(
            RegistrationRequestParams(
                login,
                password
            )
        )

    suspend fun getPosts(token: String?): List<Any> =
        token?.let {
            API.getPosts(
                it
            )
        }!!
}