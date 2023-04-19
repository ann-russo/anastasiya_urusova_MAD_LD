package com.example.mad_ld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mad_ld.navigation.Navigation
import com.example.mad_ld.ui.theme.MAD_LDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAD_LDTheme {
                Navigation()
            }
        }
    }
}