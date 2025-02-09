package io.lzaycoe.slot6

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.lzaycoe.slot6.ui.theme.Slot6Theme
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Delete

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DemoMenu() {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("None") }
    var showContextualActionBar by remember { mutableStateOf(false) }
    var showContextMenu by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // 1. App Bar with Options Menu
        CenterAlignedTopAppBar(
            title = { Text("Demo Menu") },
            actions = {
                IconButton(onClick = { showMenu = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Options")
                }
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(text = { Text("Search") }, onClick = { showMenu = false })
                    DropdownMenuItem(text = { Text("Share") }, onClick = { showMenu = false })
                    DropdownMenuItem(text = { Text("Profile") }, onClick = { showMenu = false })
                    DropdownMenuItem(text = { Text("Settings") }, onClick = { showMenu = false })
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Context Menu (Long Press)
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .combinedClickable(
                onClick = {},
                onLongClick = { showContextMenu = true }
            )
            .padding(8.dp)) {
            Text("Long press for context menu")
        }
        DropdownMenu(expanded = showContextMenu, onDismissRequest = { showContextMenu = false }) {
            DropdownMenuItem(text = { Text("Edit") }, onClick = { showContextMenu = false })
            DropdownMenuItem(text = { Text("Share") }, onClick = { showContextMenu = false })
            DropdownMenuItem(text = { Text("Delete") }, onClick = { showContextMenu = false })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Contextual Action Bar (CAB)
        if (showContextualActionBar) {
            TopAppBar(
                title = { Text("1 item selected") },
                actions = {
                    IconButton(onClick = { showContextualActionBar = false }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { showContextualActionBar = false }) {
                        Icon(Icons.Filled.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { showContextualActionBar = false }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            )
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { showContextualActionBar = !showContextualActionBar }
            .padding(8.dp)) {
            Text("Tap to select item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Popup Menu
        Button(onClick = { showPopup = true }) {
            Text("Show Popup Menu")
        }
        DropdownMenu(expanded = showPopup, onDismissRequest = { showPopup = false }) {
            DropdownMenuItem(text = { Text("Reply all") }, onClick = { showPopup = false })
            DropdownMenuItem(text = { Text("Forward") }, onClick = { showPopup = false })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDemoMenu() {
    Slot6Theme {
        DemoMenu()
    }
}
