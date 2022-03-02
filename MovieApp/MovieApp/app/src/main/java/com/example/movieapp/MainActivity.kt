package com.example.movieapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment_container) }
    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ViewModelProfile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NavigationUI.setupActionBarWithNavController(this, navController )

        binding.bottomNavigationMain.menu.findItem(R.id.profileFragment).isChecked = true
        pageSelect()


    }
    private fun pageSelect(){
        binding.bottomNavigationMain?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    if (viewModel.FillData.value == true){
                        navController.navigate(R.id.homeFragment)
                    }else{
                        Toast.makeText(this, "Register Profile", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.comingSoonFragment -> {
                    if (viewModel.FillData.value == true){
                        navController.navigate(R.id.comingSoonFragment)
                    }else{
                        Toast.makeText(this, "Register Profile", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.favoriteFragment -> {
                    if (viewModel.FillData.value == true){
                        navController.navigate(R.id.favoriteFragment)
                        Log.d("MainDataT" , viewModel.FillData.value.toString())
                    }else{
                        Toast.makeText(this, "Register Profile", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                }
            }
            true
        }
    }


}
fun btnClick(view: View){
    var i = 1
    Toast.makeText(MainActivity(), "true", Toast.LENGTH_SHORT).show()
    Toast.makeText(MainActivity(), "${view.findViewWithTag<TextView>(i).id}", Toast.LENGTH_SHORT).show()
//    Toast.makeText(this, "${findViewById<TextView>(R.id.tv11)}", Toast.LENGTH_SHORT).show()
//                view.findViewWithTag<ImageView>(i)
//        view.findViewWithTag<TextView>(i).text = "dsds"
//        view.findViewWithTag<Button>(view.tag)
//        Toast.makeText(this, "${view.id}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, "${findViewById<Button>(id)}", Toast.LENGTH_LONG).show()
//        view.findViewById<ImageView>(R.id.img11)
//        var id = view.id

}