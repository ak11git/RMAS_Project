package com.example.checkcount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.checkcount.app.PostApp
import com.example.checkcount.viewModels.MyViewModel

class MainActivity : ComponentActivity() {
    private val myViewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostApp()
        }
    }
}