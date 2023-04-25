package com.example.studybuddy20.API

import com.example.studybuddy20.DataAPIItem
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("posts")
    fun getAPIData(): Call<List<DataAPIItem>>
}