package com.example.movieapp


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.databinding.FragmentProfileBinding
import com.example.movieapp.network.NetworkManager
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.net.URI
import java.util.*


class ProfileFragment:Fragment(R.layout.fragment_profile) {

    lateinit var binding: FragmentProfileBinding
    private val viewModel:ViewModelProfile by activityViewModels()
    private lateinit var takePictureActivityResultLauncher: ActivityResultLauncher<Void>
    private lateinit var takeImageGallery : ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getImageFromGallery()
        createTakePictureIntentActivityResultLauncher()



        binding.btnRegister.setOnClickListener {
            if (binding.etFirstName.text.isNotEmpty() && binding.etLastName.text.isNotEmpty()
                &&binding.etEmailText.text.isNotEmpty()&&binding.etUsername.text.isNotEmpty()
                &&binding.etPhoneNumber.text.isNotEmpty()
            ){

                Toast.makeText(this.context, "DATA Saved", Toast.LENGTH_SHORT).show()
                viewModel.setName(binding.etFirstName.text.toString())
                viewModel.setLastName(binding.etLastName.text.toString())
                viewModel.setUserName(binding.etUsername.text.toString())
                viewModel.setEmail(binding.etEmailText.text.toString())
                viewModel.setPhoneNumber(binding.etPhoneNumber.text.toString())
                viewModel.setFillData(true)
            }else{
                Toast.makeText(this.context, "Fill DATA", Toast.LENGTH_SHORT).show()
            }
        }
        if(!viewModel.name.value.isNullOrEmpty() && !viewModel.lastName.value.isNullOrEmpty()
            && !viewModel.userName.value.isNullOrEmpty()&& !viewModel.email.value.isNullOrEmpty()
            && !viewModel.phoneNumber.value.isNullOrEmpty()){

                binding.etFirstName.setText(viewModel.name.value)
                binding.etLastName.setText(viewModel.lastName.value)
                binding.etUsername.setText(viewModel.userName.value)
                binding.etEmailText.setText(viewModel.email.value)
                binding.etPhoneNumber.setText(viewModel.phoneNumber.value)
        }

        binding.profilePic.setOnClickListener {
            AlertDialog.Builder(this.requireContext())
                .setTitle("Choose")
                .setMessage("Choose Gallery Or Camera")
                .setPositiveButton("Gallery")
                { _,_ ->
                    takeImageGallery.launch("image/*")
                }
                .setNegativeButton("Camera")
                { _,_ ->
                    takePictureActivityResultLauncher.launch(null)
                }
                .setCancelable(true)
                .show()
        }

        downloadImage()

    }
    private fun createTakePictureIntentActivityResultLauncher() {
        takePictureActivityResultLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            binding.profilePic.setImageBitmap(it)

            val stream = ByteArrayOutputStream()
            it.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val image = stream.toByteArray()
            uploadImage(image)
        }
    }
    private fun getImageFromGallery(){
        takeImageGallery = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.profilePic.setImageURI(it)
        }
    }
    private fun uploadImage(uri: ByteArray){
        val requestBody = MultipartBody.create(MediaType.parse("image/*"),uri)
        val part = MultipartBody.Part.createFormData("image" , "test81.jpg" ,requestBody)
        NetworkManager.service.uploadImage(part).enqueue(object :Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.d("image" , response.body().toString())
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d("image" , t.message.toString())
            }
        })

    }
    private fun downloadImage(){
        NetworkManager.service.getImage("test81.jpg").enqueue(object:Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("image" , t.message.toString())
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var  stream = response.body()?.byteStream()
                var  bitmap = BitmapFactory.decodeStream(stream)

                binding.profilePic.setImageBitmap(bitmap)

            }
        })
    }

}

