package com.example.sadikoi.ui.user

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.sadikoi.R
import java.io.InputStream

enum class backGround(val color: Color) {
    Color1(Color.Red),
    Color2(Color.Green),
    Color3(Color.Yellow),
    Color4(Color.Blue),
    Color5(Color.Magenta),
    Color6(Color.Cyan),
}

@Composable
fun UserPicture(
    uri: String = "",
    modifier: Modifier = Modifier,
    onImageClicked: () -> Unit = {},
    enabled: Boolean = false,
    firstLetter: String = "",
    id: Int = 0,
    photoBitmap: Bitmap? = null
) {
//    Log.d("UserPicture", "UserPictureId: $id")
//    Log.d("UserPicture", "UserPictureUri: $uri")
    TextButton(
        enabled = enabled,
        onClick = onImageClicked
    ) {
        if (uri.isBlank()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .clip(CircleShape)
                    .background(if (id == 0) Color.LightGray else backGround.entries[id % 6].color)
            ){
//                if (id != 0 && firstLetter.isNotBlank()) {
//                    Text(
//                        text = firstLetter.first().uppercase(),
//                        color = Color.Black,
//                        fontSize = MaterialTheme.typography.headlineMedium.fontSize
//                    )
//                } else {
//                    Image(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = "User picture",
////                        contentScale = ContentScale.Fit,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
                Image(
                    painter = painterResource(R.drawable.pp_test),
                    contentDescription = "User picture",
                    contentScale = ContentScale.Fit
                )
            }
//            Image(
//                imageVector = Icons.Default.Person,
//                contentDescription = "User picture",
//                contentScale = ContentScale.Crop,
//                modifier = modifier
////                    .size(100.dp)
//                    .clip(CircleShape)
//                    //            .border(1.dp, Color.Blue)
//                    .background(MaterialTheme.colorScheme.secondaryContainer),
//                colorFilter = if (enabled) ColorFilter.tint(Color.Gray) else null
//
//            )
        } else
            ImageFromGallery(modifier, uri, enabled)
    }
}


fun loadScaledBitmapFromUri(context: Context, uri: Uri, reqWidth: Int, reqHeight: Int): Bitmap? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)

        // Première passe : Décoder les dimensions
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeStream(inputStream, null, options)

        // Calculer le facteur de réduction (inSampleSize)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Deuxième passe : Décoder avec le facteur de réduction
        options.inJustDecodeBounds = false
        inputStream?.close() // Fermer et rouvrir le flux
        val newInputStream: InputStream? = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(newInputStream, null, options)

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val (height: Int, width: Int) = options.outHeight to options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val halfHeight: Int = height / 2
        val halfWidth: Int = width / 2

        while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}

fun loadBitmapFromUri(contentResolver: ContentResolver, uri: Uri): Bitmap? {
    return try {
        val timestartloadBitmapFromUri = System.currentTimeMillis()

        val timeBeforeDecoder = System.currentTimeMillis()

        /*val source =*/ if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            Log.d("abc", "VERSION.SDK_INT >= P")

//            ImageDecoder.createSource(contentResolver, uri)
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri)) { decoder, info, _ ->
//                decoder.setTargetSize(100, 100)
                decoder.setTargetSampleSize(5)
            }


        } else {
            TODO("VERSION.SDK_INT < P")

                    MediaStore.Images.Media.getBitmap(contentResolver, uri) //todo probably this
        }
//        val timeAfterDecoder = System.currentTimeMillis()
//        Log.d("time", "timeCreateSource :  ${timeAfterDecoder - timeBeforeDecoder}")
//
//
//        val timebeforeDecodeBitmap = System.currentTimeMillis()
////        ImageDecoder.decodeBitmap(source) { decoder, info, _ ->
//            ImageDecoder.decodeDrawable(source) { decoder, info, _ ->
////            Log.d("abc", "info.siz :  ${info.size}")
//
//            val timeBeforesetTargetSize = System.currentTimeMillis()
//            decoder.setTargetSize(50, 50)
//            val timeAftersetTargetSize = System.currentTimeMillis()
//            Log.d("time", "timesetTargetSize :  ${timeAftersetTargetSize - timeBeforesetTargetSize}")
//
////            if (info.size.width > 2000 || info.size.width > 4000) {
////            decoder.setTargetSampleSize(10)
//            Log.d("abc", "info.size after setTargetSampleSize :  ${info.size}")
////                } //todo what if mega image
//
//            val timeafterDecodeBitmap = System.currentTimeMillis()
//            Log.d("time", "timeDecodeBitmap :  ${timeafterDecodeBitmap - timebeforeDecodeBitmap}")
//            val timendloadBitmapFromUri = System.currentTimeMillis()
//            Log.d("time", "timeloadBitmapFromUri :  ${timendloadBitmapFromUri - timestartloadBitmapFromUri}")
//
//        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


@Composable
fun ImageFromGallery(modifier: Modifier = Modifier, uri: String, edit: Boolean = false,) {
//    val timeEnterIfromGal = System.currentTimeMillis()
//    val timebeforecontext = System.currentTimeMillis()
    val context = LocalContext.current
//    val timeaftercontext = System.currentTimeMillis()
//    Log.d("time", "context: ${timeaftercontext - timebeforecontext}")
//    val bitmap = remember(uri) { loadBitmapFromUri(context.contentResolver , uri.toUri()) }
//    val timeAfterloadBitmapFromUri = System.currentTimeMillis()
//    Log.d("time", "loadBitmapFromUri: ${timeAfterloadBitmapFromUri - timeEnterIfromGal}")
//    Log.d("time", "-------------------------------------------------------------------")
//    bitmap.

//    Log.d("UserPicture", "ImageFromGallery: $uri")
//    bitmap?.let {
////    photoBitmap?.let {
//        val timeBeforeImage = System.currentTimeMillis()
////        Log.d("bitmap", "bitmap.size: ${bitmap.width} * ${bitmap.height}")
//        Image(
////            painter = it,
//            bitmap = it.asImageBitmap(),
////            bitmap = photoBitmap.asImageBitmap(),
//            contentDescription = "User picture",
//            contentScale = ContentScale.Crop,
//            modifier = modifier
//                .size(100.dp)
//                .clip(CircleShape),
//            colorFilter = if (edit) ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }) else null
//        )
//        val timeafterImage = System.currentTimeMillis()
////        Log.d("time", "Image: ${timeafterImage - timeBeforeImage}")
//    }
    val uri = uri.toUri()

    uri.let {
        val bitmap = remember(uri) { loadScaledBitmapFromUri(context, uri, 100, 100) }
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = modifier
                .size(100.dp)
                .clip(CircleShape),
            )
        }
    }

}