package by.vfdev.constantafilms.api

import by.vfdev.constantafilms.remotemodel.Films
import by.vfdev.constantafilms.remotemodel.FilmsCallBack
import by.vfdev.constantafilms.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiFilms {

    @GET("films.json")
    suspend fun getFilms() : FilmsCallBack

    companion object {
        fun create(): ApiFilms {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiFilms::class.java)
        }
    }
}