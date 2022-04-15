package com.omdeep.kotlinmvvmproject.coroutines.coroutineConcepts.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.kotlinmvvmproject.coroutines.coroutineConcepts.repository.UserRepository
import com.omdeep.kotlinmvvmproject.coroutines.model.User
import kotlinx.coroutines.launch


class UserActivityViewModel : ViewModel(){
    var userRepository: UserRepository = UserRepository()
    lateinit var user : MutableLiveData<List<User>>


    fun getUserData(){
        viewModelScope.launch {
            var list : List<User>
//            list = userRepository.getData()
            list = userRepository.getData()

//            withContext(Dispatchers.Main){
//                list = userRepository.getData()
//            }
            Log.d("DATA", ""+list)
            user.value = list
        }

    }
}