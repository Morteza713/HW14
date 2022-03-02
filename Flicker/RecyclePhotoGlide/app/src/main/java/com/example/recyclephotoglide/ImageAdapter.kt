package com.example.recyclephotoglide

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url:String) {
    Picasso
        .get()
        .load(url)
        .into(view);
//    imageUrl="@{Flicker.photos.photo[1].url_s}"
}