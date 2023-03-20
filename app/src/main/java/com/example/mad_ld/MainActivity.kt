package com.example.mad_ld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.mad_ld.models.FavoritesModel
import com.example.mad_ld.navigation.Navigation
import com.example.mad_ld.ui.theme.MAD_LDTheme

class MainActivity : ComponentActivity() {
    private val favoritesModel: FavoritesModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAD_LDTheme {
                val favMovies by favoritesModel.favMovies.observeAsState(emptyList())
                Navigation(favMovies, favoritesModel)
            }
        }
    }
}