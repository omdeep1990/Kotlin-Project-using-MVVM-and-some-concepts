package com.omdeep.kotlinmvvmproject.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.omdeep.kotlinmvvmproject.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //TODO: Coroutine demo without using any viewBinding or DataBinding: -
    private lateinit var btnDownload : MaterialButton
    private lateinit var btnClick : MaterialButton
    private lateinit var downloadResult : MaterialTextView
    private lateinit var clickResult : MaterialTextView
    private var count : Int = 0

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