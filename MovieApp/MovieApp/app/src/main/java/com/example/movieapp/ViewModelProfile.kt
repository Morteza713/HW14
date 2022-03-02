package com.example.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelProfile:ViewModel() {

        var FillData = MutableLiveData<Boolean>()

        var name = MutableLiveData<String>()
        var lastName = MutableLiveData<String>()
        var userName = MutableLiveData<String>()
        var email = MutableLiveData<String>()
        var phoneNumber = MutableLiveData<String>()

        fun setName(name:String){
            this.name.value = name
        }
        fun setLastName(lastname:String){
            this.lastName.value = lastname
        }
        fun setUserName(username:String){
            this.userName.value = username
        }
        fun setEmail(email:String){
            this.email.value = email
        }
        fun setPhoneNumber(phonenumber:String){
            this.phoneNumber.value = phonenumber
        }

        fun setFillData(flag:Boolean){
            FillData.value = flag
        }


}