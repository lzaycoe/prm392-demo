package com.example.slot9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.slot9.ui.theme.Slot9Theme



// MainActivity - Controller
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize repository
        val userRepository = UserRepository()
        val users = userRepository.getUsers()

        setContent {
            Slot9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserList(
                        users = users,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// View
@Composable
fun UserList(users: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(users) { user ->
            UserItem(user = user)
        }
    }
}

@Composable
fun UserItem(user: User, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Name: ${user.name}")
        Text(text = "Email: ${user.email}")
    }
}

