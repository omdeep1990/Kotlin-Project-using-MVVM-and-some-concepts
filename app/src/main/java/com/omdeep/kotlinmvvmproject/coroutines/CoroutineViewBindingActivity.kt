package com.omdeep.kotlinmvvmproject.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.omdeep.kotlinmvvmproject.R
import com.omdeep.kotlinmvvmproject.databinding.ActivityCoroutineViewBindingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineViewBindingActivity : AppCompatActivity(), View.OnClickListener {
    //TODO: Binding used in Kotlin language
    private lateinit var binding : ActivityCoroutineViewBindingBinding
    private var count : Int = 0
    val myTag = "MyTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineViewBindingBinding.inflate(layoutInflater)
        //TODO: First method for giving root to setContentView
//        val view = binding.root
//        setContentView(view)

        //TODO: Second method for giving root to setContentView
        setContentView(binding.root)



        binding.btnDownload.setOnClickListener(this)
        binding.btnClick.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        //TODO:Procedure to get id in Kotlin concept and use of when instead of switch case statements
        when(view!!.id) {
            R.id.btn_download -> {
                //TODO: Defining Coroutine scope(Coroutine status).Coroutine builder
                CoroutineScope(Dispatchers.IO).launch {
                    for (i in 1L..20000L) {
                        Log.d("Downloading", "Downloading $i {${Thread.currentThread()}")
                        //TODO: Displaying the data from coroutine running thread to main thread
                        withContext(Dispatchers.Main) {
                            binding.downloadResult.text = "Downloading $i {${Thread.currentThread()}"
                        }
                    }
                }
            }
            R.id.btn_click -> {
                binding.clickResult.text = count++.toString()
            }
        }
    }
}