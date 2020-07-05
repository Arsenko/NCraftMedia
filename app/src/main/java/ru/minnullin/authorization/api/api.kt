package ru.minnullin.authorization.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.minnullin.Post
import retrofit2.http.Header

// Данные для авторизации
data class AuthRequestParams(val username: String, val password: String)

// Токен для идентификации последущих запросов
data class Token(val token: String)

// Данные для регистрации
data class RegistrationRequestParams(val username: String, val password: String)

interface API {
    // URL запроса (без учета основного адресса)
    @POST("api/v1/authentication")
    suspend fun authenticate(@Body authRequestParams: AuthRequestParams): Response<Token>

    @POST("api/v1/registration")
    suspend fun register(@Body registrationRequestParams: RegistrationRequestParams): Response<Token>

    @GET("api/v1/posts")
    suspend fun getPosts(@Header("Authorization") token: String): List<Post>
}