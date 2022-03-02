package com.example.recyclephotoglide.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PhotoService {
    @GET("services/rest")
    fun uploadImage(
        @QueryMap map: Map<String,String>,
    ): Call<Photos>
}