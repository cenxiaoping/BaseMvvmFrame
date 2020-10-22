package com.example.basemvvmframe

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.basemvvmframe.viewmodels.MainViewModels
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModels::class.java)
        btn_login.setOnClickListener {
            viewModel.login()
        }

        callBack()
    }

    private fun callBack() {
        viewModel.liveData.observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }


}