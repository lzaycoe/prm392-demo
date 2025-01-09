package com.example.slot_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.slot_3.ui.theme.Slot3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Slot3Theme {
                BasicViewsDemo()

//                UserInputDemo()

//                MeasureDemo()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Slot3Theme {
        BasicViewsDemo()

//        UserInputDemo()

//        MeasureDemo()
    }
}