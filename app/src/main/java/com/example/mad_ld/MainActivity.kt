package com.example.mad_ld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mad_ld.navigation.Navigation
import com.example.mad_ld.ui.theme.MAD_LDTheme
import com.example.mad_ld.viewmodels.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MoviesViewModel by viewModels()
        setContent {
            MAD_LDTheme {
                Navigation(viewModel)
            }
        }
    }
}