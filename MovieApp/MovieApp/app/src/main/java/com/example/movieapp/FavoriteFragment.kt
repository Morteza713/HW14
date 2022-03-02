package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.FragmentFavoriteBinding

class FavoriteFragment:Fragment(R.layout.fragment_favorite) {

    lateinit var binding: FragmentFavoriteBinding

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resources.getDrawable(view.id,null)
        binding.textView.text = view.id.toString()


        // get data from home page to show visible image and text

    }
}