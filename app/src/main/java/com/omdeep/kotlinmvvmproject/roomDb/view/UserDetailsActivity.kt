package com.omdeep.kotlinmvvmproject.roomDb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdeep.kotlinmvvmproject.R
import com.omdeep.kotlinmvvmproject.databinding.ActivityUserDetailsBinding
import com.omdeep.kotlinmvvmproject.roomDb.adapter.MyRecyclerViewAdapter
import com.omdeep.kotlinmvvmproject.roomDb.db.User
import com.omdeep.kotlinmvvmproject.roomDb.db.UserDatabase
import com.omdeep.kotlinmvvmproject.roomDb.factory.UserDetailsViewModelFactory
import com.omdeep.kotlinmvvmproject.roomDb.repository.UserRepository
import com.omdeep.kotlinmvvmproject.roomDb.viewModel.UserDetailsViewModel

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserDetailsBinding
    private lateinit var userViewModel : UserDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //TODO: Initializing all the MVVM concepts used here
        val dao = UserDatabase.getInstance(this).dao
        val userRepository = UserRepository(dao)
        val factory = UserDetailsViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, factory)[UserDetailsViewModel::class.java]

        //TODO: binding.userViewModel is the variable defined in its xml layout file
        binding.userViewModel = userViewModel
        //TODO: Giving lifecycle owner to layout file
        binding.lifecycleOwner = this

        displayUsersList()

    }

    fun displayUsersList() {
        userViewModel.userList.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(it) { selectedItem: User ->
                onItemClickListener(
                    selectedItem
                )
            }
        })
    }

    private fun onItemClickListener(user : User) {
        Toast.makeText(this, "Selected name is ${user.firstName+" " +
        user.lastName}", Toast.LENGTH_SHORT).show()

        userViewModel.updateOrDeleteClick(user)
    }
}