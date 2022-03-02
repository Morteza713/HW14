package com.example.recyclephotoglide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclephotoglide.network.Flicker
import com.squareup.picasso.Picasso

class Adaptor(var flicker:Flicker) : RecyclerView.Adapter<Adaptor.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var ivPhoto = itemView.findViewById<ImageView>(R.id.ivPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.photo_recyclerview,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = flicker.photos.photo[position]

        Picasso.get().load(photo.url_s).into(holder.ivPhoto);
    }

    override fun getItemCount(): Int {
        return flicker.photos.photo.count()
    }

}