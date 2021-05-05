package com.example.projetofinal.Interface

import com.example.projetofinal.Model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsService {

    @GET
    fun getNewsFromSource(@Url url: String): Call<News>

}