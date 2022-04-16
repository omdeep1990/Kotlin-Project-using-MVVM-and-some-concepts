package com.omdeep.kotlinmvvmproject.roomDb.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.kotlinmvvmproject.roomDb.db.User
import com.omdeep.kotlinmvvmproject.roomDb.repository.UserRepository
import kotlinx.coroutines.launch

//TODO: Create constructor of UserRepository here
class UserDetailsViewModel(val userRepository: UserRepository) : ViewModel() {

    var firstName = MutableLiveData<String?>()
    var lastName = MutableLiveData<String?>()
    var mobileNo = MutableLiveData<String?>()

    var saveOrUpdate = MutableLiveData<String>()
    var clerAllOrDelete = MutableLiveData<String>()

    //TODO: Initializing values to any button or text view at first time
    init {
        //TODO: Setting value to Mutable Live Data
        saveOrUpdate.value = "SAVE"
        clerAllOrDelete.value = "CLEAR ALL"
    }

    fun saveOrUpdateClick() {
        //TODO: ON Button click setting values to these editTexts
        val fName = firstName.value!!
        val lName = lastName.value!!
        val mobNo = mobileNo.value!!
        insert(User(0, fName, lName, mobNo))

        //TODO: Assigning null value to these editTexts after completion
        firstName.value = null
        lastName.value = null
        mobileNo.value = null

    }
    //TODO: Calling insert function and doing task in background using coroutines viewModel scope
    fun insert(user: User){
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }

    //TODO: Calling clearAll function and doing task in background using coroutines viewModel scope
    fun clearOrDeleteAll(){
        viewModelScope.launch {
            userRepository.deleteAll()
        }
    }

    //TODO: Function for displaying data in recyclerView
    val userList = userRepository.users
}