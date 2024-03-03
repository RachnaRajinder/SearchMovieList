package com.example.project_movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project_movieapp.presentation.navigation.AppNavigation
import com.example.project_movieapp.ui.theme.Project_MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project_MovieAppTheme {
                AppNavigation()
            }
        }
    }
}
