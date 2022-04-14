package com.omdeep.kotlinmvvmproject.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.omdeep.kotlinmvvmproject.R
import com.omdeep.kotlinmvvmproject.databinding.ActivityCoroutineDemoBinding
import kotlinx.coroutines.*

class CoroutineDemo : AppCompatActivity(), View.OnClickListener {
    lateinit var binding : ActivityCoroutineDemoBinding
    val myTag = "MyTag"
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDownload.setOnClickListener(this)
        binding.btnClick.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view!!.id) {
            R.id.btn_download ->{
                CoroutineScope(Dispatchers.IO).launch {
                    userDownload()
                    Log.d(myTag, "Calculation startted...")
                    val task1 = task1()
                    val task2 = task2()

                    val total = task1.plus(task2)

                    Log.d(myTag, "Total $total")
                }
            }

            R.id.btn_click ->{
                binding.clickResult.text = count++.toString()
            }
        }

    }
    suspend fun userDownload() {
        for (i in 1..1000) {
            withContext(Dispatchers.Main) {
                binding.downloadResult.text = "Downloading $i ${Thread.currentThread().name}"
            }
        }
    }

    private suspend fun task1() : Int{
        Log.d(myTag, "task 1 returned")
        delay(10000)
        return 10000
    }

    private suspend fun task2() : Int{
        Log.d(myTag, "task 2 returned")
        delay(8000)
        return 8000
    }
}