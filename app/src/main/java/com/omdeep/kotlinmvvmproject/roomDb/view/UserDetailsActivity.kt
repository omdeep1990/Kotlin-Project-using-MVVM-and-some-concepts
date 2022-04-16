package com.omdeep.kotlinmvvmproject.roomDb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.omdeep.kotlinmvvmproject.R
import com.omdeep.kotlinmvvmproject.databinding.ActivityUserDetailsBinding
import com.omdeep.kotlinmvvmproject.roomDb.db.UserDatabase
import com.omdeep.kotlinmvvmproject.roomDb.factory.UserDetailsViewModelFactory
import com.omdeep.kotlinmvvmproject.roomDb.repository.UserRepository
import com.omdeep.kotlinmvvmproject.roomDb.viewModel.UserDetailsViewModel

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)

        //TODO: Initializing all the MVVM concepts used here
        var dao = UserDatabase.getInstance(this).dao
        val userRepository = UserRepository(dao)
        val factory = UserDetailsViewModelFactory(userRepository)
        val userViewModel = ViewModelProvider(this, factory)[UserDetailsViewModel::class.java]

        //TODO: binding.userViewModel is the variable defined in its xml layout file
        binding.userViewModel = userViewModel
        //TODO: Giving lifecycle owner to layout file
        binding.lifecycleOwner = this
    }
}