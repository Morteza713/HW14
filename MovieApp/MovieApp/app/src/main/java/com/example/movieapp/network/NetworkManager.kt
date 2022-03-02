package com.example.movieapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://51.195.19.222")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(ImageService::class.java)

}