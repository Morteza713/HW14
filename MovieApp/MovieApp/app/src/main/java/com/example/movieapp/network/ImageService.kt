package com.example.movieapp.network

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ImageService {

    @GET("http://51.195.19.222/uploads/{photoname}")
    fun getImage(@Path("photoname") photoName: String?): Call<ResponseBody>

    @Multipart
    @POST("http://51.195.19.222/users/MORTEZA")
    fun uploadImage(
        @Part image: MultipartBody.Part,
    ): Call<Any>
}