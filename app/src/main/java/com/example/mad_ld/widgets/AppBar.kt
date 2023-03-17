package com.example.mad_ld.widgets


import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun AppBar() {
    var dropDownMenuExpanded by remember { mutableStateOf(false) }
    val contextForToast = LocalContext.current.applicationContext

    TopAppBar(
        title = { Text(text = "Movies") },
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
                    Toast.makeText(contextForToast, "Favorites Click", Toast.LENGTH_SHORT)
                        .show()
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