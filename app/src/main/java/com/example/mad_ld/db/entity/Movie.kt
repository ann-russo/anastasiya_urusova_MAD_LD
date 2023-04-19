package com.example.mad_ld.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val year: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val rating: String,
    val images: List<String>,
    var isFavorite: Boolean = false
) {
}