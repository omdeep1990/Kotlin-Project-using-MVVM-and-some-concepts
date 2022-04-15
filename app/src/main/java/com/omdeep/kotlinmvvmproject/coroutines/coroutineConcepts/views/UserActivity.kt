package com.omdeep.kotlinmvvmproject.coroutines.coroutineConcepts.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omdeep.kotlinmvvmproject.R
import com.omdeep.kotlinmvvmproject.coroutines.coroutineConcepts.viewModels.UserActivityViewModel

class UserActivity : AppCompatActivity() {
    lateinit var viewModel : UserActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        viewModel = ViewModelProvider(this)[UserActivityViewModel::class.java]
        viewModel.getUserData()

        viewModel.user.observe(this, Observer { myList ->
            myList.forEach {
                Log.d("MyTag", "name is ${it.name}")
            }
        })


    }
}