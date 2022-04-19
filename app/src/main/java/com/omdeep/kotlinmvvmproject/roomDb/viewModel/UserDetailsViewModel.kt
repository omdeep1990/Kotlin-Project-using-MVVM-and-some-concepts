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
    var clearAllOrDelete = MutableLiveData<String>()

    var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete : User

    //TODO: Initializing values to any button or text view at first time
    init {
        //TODO: Setting value to Mutable Live Data
        saveOrUpdate.value = "SAVE"
        clearAllOrDelete.value = "CLEAR ALL"
    }

    fun saveOrUpdateClick() {
        if (isUpdateOrDelete) {
            userToUpdateOrDelete.firstName = firstName.value!!
            userToUpdateOrDelete.lastName = lastName.value!!
            userToUpdateOrDelete.mobileNo = mobileNo.value!!

            update(userToUpdateOrDelete)

            setNullValue()

            saveOrUpdate.value = "Save"
            clearAllOrDelete.value = "Clear All"
        } else {
            //TODO: ON Button click setting values to these editTexts
            val fName = firstName.value!!
            val lName = lastName.value!!
            val mobNo = mobileNo.value!!
            insert(User(0, fName, lName, mobNo))

            //TODO: Assigning null value to these editTexts after completion
           setNullValue()
        }
    }

        fun updateOrDeleteClick(user: User) {
            firstName.value = user.firstName
            lastName.value = user.lastName
            mobileNo.value = user.mobileNo

            isUpdateOrDelete = true
            userToUpdateOrDelete = user

            saveOrUpdate.value = "Update"
            clearAllOrDelete.value = "Delete"
        }

        fun clearAllOrDelete() {
            if (isUpdateOrDelete) {
                delete(userToUpdateOrDelete)

                setNullValue()

                saveOrUpdate.value = "Save"
                clearAllOrDelete.value = "Clear All"
            } else {
                clearAll()
            }
        }

    //TODO: Calling insert function and doing task in background using coroutines viewModel scope
    private fun insert(user: User){
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }

    //TODO: Calling clearAll function and doing task in background using coroutines viewModel scope
    private fun clearAll(){
        viewModelScope.launch {
            userRepository.deleteAll()
        }
    }

    //TODO: Calling update function and doing task in background using coroutines viewModel scope
    private fun update(user: User) {
        viewModelScope.launch {
            userRepository.update(user)
        }
    }

    //TODO: Calling delete function and doing task in background using coroutines viewModel scope
    private fun delete(user: User) {
        viewModelScope.launch {
            userRepository.delete(user)
        }
    }

    private fun setNullValue() {
        firstName.value = null
        lastName.value = null
        mobileNo.value = null
    }

    //TODO: Function for displaying data in recyclerView
    val userList = userRepository.users
}