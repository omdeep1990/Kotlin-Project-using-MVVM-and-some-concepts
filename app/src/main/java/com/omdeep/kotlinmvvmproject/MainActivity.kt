package com.omdeep.kotlinmvvmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btnDownload : MaterialButton
    lateinit var btnClick : MaterialButton
    lateinit var downloadResult : MaterialTextView
    lateinit var clickResult : MaterialTextView
    var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDownload = findViewById(R.id.btn_download)
        btnClick = findViewById(R.id.btn_click)
        downloadResult = findViewById(R.id.download_result)
        clickResult = findViewById(R.id.click_result)

        btnDownload.setOnClickListener(this)
        btnClick.setOnClickListener(this)
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
                            downloadResult.text = "Downloading $i {${Thread.currentThread()}"
                        }
                    }
                }
            }
            R.id.btn_click -> {
                clickResult.text = count++.toString()
            }
        }
    }
}