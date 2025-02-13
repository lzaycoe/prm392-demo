package com.example.slot7

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ClearingData : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClearingDataScreen(this)
        }
    }
}

@Composable
fun ClearingDataScreen(context: Context) {
    // Khai báo SharedPreferences
    val sharedPref = remember { context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE) }

    // State để lưu trữ dữ liệu hiển thị
    var data by remember { mutableStateOf(loadInitialData(sharedPref)) }

    // Hàm để nạp lại dữ liệu ban đầu
    fun reloadData() {
        data = loadInitialData(sharedPref)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hiển thị tiêu đề cột
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Tiêu đề cột Key
            Text(
                text = "Key",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            // Tiêu đề cột Value
            Text(
                text = "Value",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            // Tiêu đề cột Action
            Text(
                text = "Action",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }

        // Hiển thị bảng dữ liệu
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(data) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Cột Key
                    Text(
                        text = item.key,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    // Cột Value
                    Text(
                        text = item.value.toString(),
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )

                    // Nút xóa (Action)
                    Button(
                        onClick = {
                            // Xóa giá trị cụ thể
                            sharedPref.edit().remove(item.key).apply()
                            // Cập nhật lại dữ liệu hiển thị
                            reloadData()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }

        // Nút xóa tất cả
        Button(
            onClick = {
                // Xóa toàn bộ dữ liệu
                sharedPref.edit().clear().apply()
                // Cập nhật lại dữ liệu hiển thị
                reloadData()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Delete All")
        }

        // Nút nạp lại dữ liệu ban đầu
        Button(
            onClick = {
                // Lưu lại dữ liệu ban đầu
                saveInitialData(sharedPref)
                // Cập nhật lại dữ liệu hiển thị
                reloadData()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Reload Data")
        }
    }
}

// Hàm để lưu dữ liệu ban đầu vào SharedPreferences
private fun saveInitialData(sharedPref: android.content.SharedPreferences) {
    sharedPref.edit().apply {
        putString("user_key", "JohnDoe")
        putInt("age_key", 25)
        putBoolean("is_subscribed", true)
    }.apply()
}

// Hàm để tải dữ liệu từ SharedPreferences
private fun loadInitialData(sharedPref: android.content.SharedPreferences): List<DataItem> {
    return listOf(
        DataItem("user_key", sharedPref.getString("user_key", "Default") ?: "Default"),
        DataItem("age_key", sharedPref.getInt("age_key", -1)),
        DataItem("is_subscribed", sharedPref.getBoolean("is_subscribed", false))
    )
}

// Data class để lưu trữ thông tin key-value
data class DataItem(val key: String, val value: Any)