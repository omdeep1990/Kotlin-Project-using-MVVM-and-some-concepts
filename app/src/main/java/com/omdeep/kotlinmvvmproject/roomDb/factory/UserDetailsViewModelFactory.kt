package com.omdeep.kotlinmvvmproject.roomDb.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.kotlinmvvmproject.roomDb.repository.UserRepository

import com.omdeep.kotlinmvvmproject.roomDb.viewModel.UserDetailsViewModel
import java.lang.IllegalArgumentException

//TODO: Create UserRepository constructor here
class UserDetailsViewModelFactory(val userRepository: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(userRepository) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }
}