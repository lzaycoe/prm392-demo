package com.example.slot7

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class RetrievingData : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrieveDataScreen()
        }
    }
}

@Composable
fun RetrieveDataScreen() {
    val context = LocalContext.current

    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    var inputInteger by remember { mutableStateOf(TextFieldValue("")) }
    var inputBoolean by remember { mutableStateOf(false) }
    var retrievedText by remember { mutableStateOf("") }
    var retrievedInteger by remember { mutableStateOf(0) }
    var retrievedBoolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input text
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Input text:") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input integer
        TextField(
            value = inputInteger,
            onValueChange = { inputInteger = it },
            label = { Text("Input integer:") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input boolean
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = inputBoolean,
                onCheckedChange = { inputBoolean = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Input boolean")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save data
        Button(
            onClick = {
                saveToSharedPreferences(context, "key_text", inputText.text)
                saveToSharedPreferences(context, "key_integer", inputInteger.text.toIntOrNull() ?: 0)
                saveToSharedPreferences(context, "key_boolean", inputBoolean)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Retrieve data
        Button(
            onClick = {
                retrievedText = getFromSharedPreferences(context, "key_text", "")
                retrievedInteger = getFromSharedPreferences(context, "key_integer", 0)
                retrievedBoolean = getFromSharedPreferences(context, "key_boolean", false)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retrieve data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Text: $retrievedText")
        Text("Integer: $retrievedInteger")
        Text("Boolean: $retrievedBoolean")
    }
}

// Hàm lưu dữ liệu vào Shared Preferences
fun saveToSharedPreferences(context: Context, key: String, value: Any) {
    val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    when (value) {
        is String -> editor.putString(key, value)
        is Int -> editor.putInt(key, value)
        is Boolean -> editor.putBoolean(key, value)
        else -> throw IllegalArgumentException("Unsupported data type")
    }
    editor.apply()
}

// Hàm lấy dữ liệu từ Shared Preferences
inline fun <reified T> getFromSharedPreferences(context: Context, key: String, defaultValue: T): T {
    val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    return when (T::class) {
        String::class -> sharedPreferences.getString(key, defaultValue as? String ?: "") as T
        Int::class -> sharedPreferences.getInt(key, defaultValue as? Int ?: 0) as T
        Boolean::class -> sharedPreferences.getBoolean(key, defaultValue as? Boolean ?: false) as T
        else -> throw IllegalArgumentException("Unsupported data type")
    }
}
