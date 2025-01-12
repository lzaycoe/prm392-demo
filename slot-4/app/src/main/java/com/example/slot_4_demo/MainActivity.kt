package com.example.slot_4_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.slot_4_demo.ui.theme.Slot4demoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Slot4demoTheme {
            }
        }
    }
}

@Composable
fun BlueText() {
    Text("Hello World", color = Color.Blue)
}

@Composable
fun BigText() {
    Text("Hello World", fontSize = 30.sp)
}

@Composable
fun ItalicText() {
    Text("Hello World", fontStyle = FontStyle.Italic)
}

@Composable
fun BoldText() {
    Text("Hello World", fontWeight = FontWeight.Bold)
}

@Composable
fun TextShadow() {
    val offset = Offset(5.0f, 5.0f)
    Text(
        text = "Hello world!",
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue, offset = offset, blurRadius = 3f
            )
        )
    )
}

@Composable
fun MultipleStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
        }
    )
}

// --------------------------------------------------

@Composable
fun CenterText() {
    Text(
        "Hello World",
        textAlign = TextAlign.Center,
        modifier = Modifier.width(150.dp)
    )
}

@Composable
fun ParagraphStyle() {
    Text(
        text = "This is a sample text",
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                )
            )
        )
    )

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Slot4demoTheme {
//        BlueText()
//        BigText()
//        ItalicText()
//        BoldText()
//        TextShadow()
//        MultipleStylesInText()

// --------------------------------------------------

//        CenterText()
//        ParagraphStyle()
    }
}