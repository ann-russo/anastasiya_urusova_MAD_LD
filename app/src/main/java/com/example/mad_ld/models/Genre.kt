package com.example.mad_ld.models

enum class Genre {
    ACTION,
    ADVENTURE,
    BIOGRAPHY,
    CRIME,
    COMEDY,
    DOCUMENTARY,
    DRAMA,
    FANTASY,
    HISTORY,
    HORROR,
    MYSTERY,
    ROMANCE,
    SCIFI,
    THRILLER
}

data class ListItemSelectable(
    val title: String,
    var isSelected: Boolean
)