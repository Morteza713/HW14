package com.example.recyclephotoglide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.recyclephotoglide.databinding.ActivityMainBinding
import com.example.recyclephotoglide.network.Flicker
import com.example.recyclephotoglide.network.NetworkManager
import com.example.recyclephotoglide.network.Photo
import com.example.recyclephotoglide.network.Photos
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

//    lateinit var image :ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main)
//        image =  findViewById<ImageView>(R.id.image)
//        var url = ""
        NetworkManager.service.uploadImage(mapOf(
            "api_key" to "1c04e05bce6e626247758d120b372a73",
            "method" to "flickr.photos.getRecent",
            "extras" to "url_s",
            "format" to "json",
            "nojsoncallback" to "1",
            "per_page" to "100",
            "page" to "1"
        )).enqueue(object :Callback<Photos>{
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                Log.d("image","${response.body()?.photo?.get(1)?.url_s.toString()}")
//                url = response.body()?.photo?.get(1)?.url_s.toString()
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                Log.d("image","${t.message}")
            }
        }
        )

//        Picasso.get().load(url).into(image);

    }
}


