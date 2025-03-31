package com.example.todoandroidsimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todoandroidsimple.ui.AppNavGraph
import com.example.todoandroidsimple.ui.theme.TodoAndroidSimpleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoAndroidSimpleTheme {
                AppNavGraph()
            }
        }
    }
}
