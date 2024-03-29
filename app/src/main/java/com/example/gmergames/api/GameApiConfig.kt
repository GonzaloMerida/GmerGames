package com.example.myapplication.api

import com.api.igdb.request.TwitchAuthenticator
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Request
import java.util.concurrent.TimeUnit

class GameApiConfig {
    companion object {

        const val BASE_URL : String = "https://api.igdb.com/v4/"

        const val CLIENT_ID = "bp7x7j13buptcuvzj07iyq4n2qygn0"

        const val CLIENT_SECRET = "zm16un1akftpq2c21erab9032tbv91"

        val token = TwitchAuthenticator.requestTwitchToken(CLIENT_ID, CLIENT_SECRET)

        //https://id.twitch.tv/oauth2/token?client_id=bp7x7j13buptcuvzj07iyq4n2qygn0&client_secret=zm16un1akftpq2c21erab9032tbv91&grant_type=client_credentials

        //Bearer 2nbym8tmkyx8wjxx7qxpulkfya0pfa

        //Definición de la api de Retrofit2.
        fun provideRetrofit(): Retrofit {

            //Constructor GSON
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()

            //Peticiones HTTP
            val builder = OkHttpClient.Builder()

            val headers = Interceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .addHeader("Client-ID", CLIENT_ID)
                    .addHeader("Authorization", token.toString())
                    .build()
                chain.proceed(request)
            }

            //Cliente para las peticiones http
            val client = builder
                .readTimeout(50, TimeUnit.SECONDS)
                .connectTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(headers)
                .build()

            //Instancia de Retrofit
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }
    }
}