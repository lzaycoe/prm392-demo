package com.example.slot5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    startActivity(Intent(this@MainActivity, OverviewActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Overview Activity Demo", color = Color.White, style = TextStyle(fontSize = 18.sp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    startActivity(Intent(this@MainActivity, DefinitionActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5))
            ) {
                Text("Definition Activity Demo", color = Color.White, style = TextStyle(fontSize = 18.sp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    startActivity(Intent(this@MainActivity, LifecycleActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4081))
            ) {
                Text("Lifecycle & Callbacks Activity Demo", color = Color.White, style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}
