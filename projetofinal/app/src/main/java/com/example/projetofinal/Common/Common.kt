package com.example.projetofinal.Common

import com.example.projetofinal.Interface.NewsService
import com.example.projetofinal.Remote.RetrofitClient
import java.lang.StringBuilder
import javax.xml.transform.Source

object Common {
    val BASE_URL = "https://newsapi.org/"
    val API_KEY = "7cc6f9c8dfa44fe993467007ada10a9e"

    val newsService: NewsService
        get() = RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)

    fun getNewsAPI(): String {
        val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?country=")
            .append("br")
            .append("&apiKey=")
            .append(API_KEY)
            .toString()

        return apiUrl
    }
}