package io.lzaycoe.slot6

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.lzaycoe.slot6.ui.theme.Slot6Theme

@Composable
fun DemoDisplayImages() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Image 1",
//        modifier = Modifier
//            .padding(16.dp)
//            .border(2.dp, Color.Red)
    )
}

@Preview(
    showBackground = true, widthDp = 390, heightDp = 900
)
@Composable
fun DemoDisplayImagesPreview() {
    Slot6Theme {
        DemoDisplayImages()
    }
}