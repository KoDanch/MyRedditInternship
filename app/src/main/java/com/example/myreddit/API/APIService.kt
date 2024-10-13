package com.example.myreddit.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("top.json")
    suspend fun getTopPostReddit(
        @Query("limit") limit: Int,
        @Query("t") intervalPost: String,
        @Query("sort") sortPost: String
    ): APIModel.APIModelResponse

    @GET("{subreddit_name}/about.json")
    suspend fun getAboutSubreddit(
        @Path("subreddit_name") subreddit_name: String
    ): APIModel.APIModelAboutResponse
}

object Instance {
    private val URl = "https://www.reddit.com/"
    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

}