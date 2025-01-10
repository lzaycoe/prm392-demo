package com.example.slot_3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slot_3.ui.theme.Slot3Theme

@Composable
fun BasicViewsDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()

        PartDivider()

        ButtonGroup()

        PartDivider()


    }
}

@Composable
fun WelcomeText(){
    Text(
        text = "Slot 3 - Code Demo \n\n \uD83E\uDDA5 Group 1",
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(bottom = 16.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun PartDivider(){
    HorizontalDivider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, top = 50.dp)
    )
}

@Composable
fun ButtonGroup(){
    var buttonName by remember { mutableStateOf("None") }

    Text(text = "Clicked button: $buttonName")

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
        buttonName = "Filled Button"
    }) {
        Text("Filled Button")
    }
    Spacer(modifier = Modifier.height(16.dp))
    FilledTonalButton(onClick = {
        buttonName = "Filled Tonal Button"
    }) {
        Text("Filled Tonal Button")

    }
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedButton(onClick = {
        buttonName = "Outlined Button"
    }) {
        Text("Outlined Button")
    }
    Spacer(modifier = Modifier.height(16.dp))
    ElevatedButton(onClick = {
        buttonName = "Elevated Button"
    }) {
        Text("Elevated Button")
    }
    Spacer(modifier = Modifier.height(16.dp))
    TextButton(onClick = {
        buttonName = "Text Button"
    }) {
        Text("Text Button")
    }
}

