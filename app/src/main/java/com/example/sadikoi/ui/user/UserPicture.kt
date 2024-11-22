package com.example.sadikoi.ui.user

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun UserPicture(
    uri: String = "",
    modifier: Modifier = Modifier,
    onImageClicked: () -> Unit = {},
    enabled: Boolean = false,
) {
    Log.d("USERSCREEN", "UserPicture: $uri")
//    Log.d("USERSCREEN", "UserPictuqweqre: ${uri ?: "null"}")
//    Log.d("USERSCREEN", "uri.isNullOrBlank(): ${uri.isNullOrEmpty()}")
//    Log.d("USERSCREEN", "{uri.equals(null)}: ${uri.equals(null)}")
    // circulaire image
    TextButton(
        enabled = enabled,
        onClick = onImageClicked
    ) {
        if (uri.isBlank()) {
            Log.d("USERSCREEN", "UserPicture: $uri")
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "User picture",
                contentScale = ContentScale.Crop,
                modifier = modifier
//                    .size(100.dp)
                    .clip(CircleShape)
                    //            .border(1.dp, Color.Blue)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                colorFilter = if (enabled) ColorFilter.tint(Color.Gray) else null

            )
        }
        else
            ImageFromGallery(modifier, uri, enabled)
    }
}

fun loadBitmapFromUri(contentResolver: ContentResolver, uri: Uri): Bitmap? {
    return try {


        val source = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.createSource(contentResolver, uri)
        } else {
            TODO("VERSION.SDK_INT < P")
            //        MediaStore.Images.Media.getBitmap(contentResolver, uri) //todo probably this
        }
        ImageDecoder.decodeBitmap(source) { decoder, info, _ ->
            Log.d("abc", "info.siz :  ${info.size}")
//            decoder.setTargetSize(100, 100)
//            if (info.size.width > 2000 || info.size.width > 4000) {
            decoder.setTargetSampleSize(4)
            Log.d("abc", "info.size after setTargetSampleSize :  ${info.size}")
//                } //todo what if mega image

        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun ImageFromGallery(modifier: Modifier = Modifier, uri: String, edit: Boolean = false) {
    Log.d("nfwnfiuqw11", "ImageFromGallery: $uri")
    val context = LocalContext.current
    val bitmap = loadBitmapFromUri(context.contentResolver , uri.toUri())
//    bitmap.

    Log.d("nfwnfiuqw", "ImageFromGallery: $uri")
    bitmap?.let {
        Log.d("bitmap", "bitmap.size: ${bitmap.width} * ${bitmap.height}")
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "User picture",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(100.dp)
                .clip(CircleShape),
            colorFilter = if (edit) ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }) else null
        )
    }

}