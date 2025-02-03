package com.example.slot5

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// Create class DefinitionActivity inheriting ComponentActivity
class DefinitionActivity : ComponentActivity() {
    // Override the onCreate method to set the content of the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the super class onCreate method
        super.onCreate(savedInstanceState)
        // Set the content of the activity
        setContent {
            // Call the MyApp composable function
            MyApp()
        }
    }

    fun gobackToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

// Create a composable function MyApp
@SuppressLint("ContextCastToActivity")
@Composable
fun MyApp() {
    val activity = LocalContext.current as DefinitionActivity
    var text by remember { mutableStateOf("This is Definition Activity!") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { text = "Button clicked!" }) {
            Text("Click me!")
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {
            activity.gobackToMainActivity()
        }) {
            Text("<- Go back")
        }
    }
}



