package com.example.mad_ld.models

enum class Genre {
    Action,
    Adventure,
    Biography,
    Crime,
    Comedy,
    Documentary,
    Drama,
    Fantasy,
    History,
    Horror,
    Mystery,
    Romance,
    Scifi,
    Thriller
}

data class ListItemSelectable(
    val title: String,
    var isSelected: Boolean
)