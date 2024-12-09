package com.ilham.data.remote.service

import com.ilham.data.remote.dto.NewsResponse
import com.ilham.util.Constans.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everythings")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("source") source: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}