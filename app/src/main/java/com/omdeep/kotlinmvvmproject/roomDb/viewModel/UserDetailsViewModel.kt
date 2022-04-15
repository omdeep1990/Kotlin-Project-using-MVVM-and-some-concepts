package com.omdeep.kotlinmvvmproject.roomDb.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omdeep.kotlinmvvmproject.roomDb.repository.UserRepository

//TODO: Create constructor of UserRepository here
class UserDetailsViewModel(val userRepository: UserRepository) : ViewModel() {

    var firstName = MutableLiveData<String?>()
    var lastName = MutableLiveData<String?>()
    var mobileNo = MutableLiveData<String?>()

    var saveOrUpdate = MutableLiveData<String>()
    var clerAllOrDelete = MutableLiveData<String>()

    init {
        //TODO: Setting value to Mutable Live Data
        saveOrUpdate.value = "SAVE"
        clerAllOrDelete.value = "CLEAR ALL"
    }
}