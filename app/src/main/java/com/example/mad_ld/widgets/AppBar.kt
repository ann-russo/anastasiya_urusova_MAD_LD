package com.example.mad_ld.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AppBar(navController: NavController = rememberNavController(), title: String) {
    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        navigationIcon =
        if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        },
        title = { Text(text = title) },
        backgroundColor =  MaterialTheme.colors.primarySurface,
        actions = {
            TopAppBarActionButton(
                imageVector = Icons.Outlined.MoreVert,
                description = "Dropdown Menu") {
                dropDownMenuExpanded = true
            }
            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = {
                    dropDownMenuExpanded = false
                },
                offset = DpOffset(x = 10.dp, y = (-60).dp)
            ) {
                DropdownMenuItem(onClick = {
                    navController.navigate("favorites")
                    dropDownMenuExpanded = false
                }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorites")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Favorites")
                }
            }
        }
    )
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}