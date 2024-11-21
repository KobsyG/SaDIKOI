package com.example.sadikoi.ui.user

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.AppViewModelProvider
import com.example.sadikoi.ui.theme.SaDIKOITheme
//import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.net.toUri
import com.example.sadikoi.R
import com.example.sadikoi.SadikoiApp

@Composable
fun BarInfo(
    modifier: Modifier = Modifier,
    barName: String,
    text: @Composable () -> Unit
) {
        Row(
            //                horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
//                            .border(1.dp, Color.Blue)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(50.dp)
                )
                .height(TextFieldDefaults.MinHeight)
                .clip(RoundedCornerShape(50.dp))
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = barName,
//                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight()
//                    .clip(RoundedCornerShape(50.dp))
//                                .border(1.dp, Color.Green)

            )
            text()
        }
}

//fun resizeBitmap

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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //todo a garder ?
@Composable
fun UserScreen(
    viewModel: UserViewModel, //todo virer le "?" utile pour la preview
    toAddUser: Boolean = false,
    onSendMessageClicked: (Int, String, String) -> Unit,
    onEditUserClicked: (Int) -> Unit,
    user: UserUiState = viewModel.uiState.collectAsState().value,
    navigateBack: () -> Unit,
    backHandle: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler() {
        backHandle()
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onSendMessageClicked(
                    user.id,
                    user.number,
                    if (user.firstName.isNotBlank()) user.firstName else user.number
                ) }
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "send Message"
                )
            }
        }
    ) {

        val coroutineScope = rememberCoroutineScope() //todo pas ici probablement

        Column(
//            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
//                .border(1.dp, Color.Red)
        ) {
            Row(
//                horizontalArrangement = Arrangement.SpaceBetween, //todo comment centrer picture
                modifier = Modifier
                    .fillMaxWidth()
//                    .border(1.dp, Color.Blue)
            ) {
//                Button(
//                    onClick = {
//                        coroutineScope.launch {
//                            viewModel?.deleteUser() //todo virer le "?" utile pour la preview
//                            navigateBack()
//                        }
//                    }
//                ) {
//                    Icon(
//                        Icons.Default.Delete,
//                        contentDescription = "send Message"
//                    )
//                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
//                UserPicture(modifier = Modifier.weight(1f).border(1.dp, Color.Blue))
                UserPicture(uri = user.photoPath, modifier = Modifier.size(100.dp))
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondaryContainer),
                        onClick = {
                            onEditUserClicked(user.id)
                        }, //todo use the same screen que userAdd mais prérempli + addUser devient modifier user
//                        modifier = Modifier
//                        .wrapContentWidth()
//                            .border(1.dp, Color.Green)
//                            .weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "edit user",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
            BarInfo(barName = stringResource(R.string.last_name), text = {Text(
                text = user.lastName,
//                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
//                    .padding(8.dp)
                    .weight(3f)
//                    .background(MaterialTheme.colorScheme.primaryContainer)
            )} )

            BarInfo(barName = stringResource(R.string.first_name), text = {Text(
                text = user.firstName,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(3f)
            )})

            BarInfo(barName = stringResource(R.string.number), text = {Text(
                text = user.number,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(3f)
            )})

            BarInfo(barName = stringResource(R.string.mail), text = {Text(
                text = user.mail,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(3f)
            )})

            BarInfo(barName = stringResource(R.string.passion), text = {Text(
                text = user.passion,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(3f)
            )})



//            Row(
////                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = "Nom",
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
//                        .border(1.dp, Color.Blue)
//
//                )
//                Text(
//                    text = user.lastName,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .weight(3f)
//                    )
//            }
//            Row(
////                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = "Prenom",
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
//                    )
//
//                Text(
//                    text = user.firstName,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .weight(3f)
//
//                )
//            }
//            Row(
////                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = "Numero",
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
//                )
//                Text(
//                    text = user.number.toString(),
//                 modifier = Modifier
//                     .padding(8.dp)
//                     .weight(3f)
//                ) //todo number String direct ?
//            }
//            Row(
////                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = "Mail",
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
//                    )
//                Text(
//                    text = user.mail,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .weight(3f)
//                )
//            }
//            Row(
////                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = "Passion",
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
//                    )
//                Text(
//                    text = user.passion,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .weight(3f)
//                    )
//            }
//            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .border(1.dp, Color.Blue)
//            ) {
////                Button(
////                    onClick = {
////                        onEditUserClicked(user.id)
////                    } //todo use the same screen que userAdd mais prérempli + addUser devient modifier user
////                ) {
////                    Text("edit User")
////                }
////                Button(
////                    onClick = {
////                        onSendMessageClicked(user.id, user.number)
////                    }
////                ) {
////                    Text("send Message")
////                }
//            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun UserScreenPreview(){
//    SaDIKOITheme {
//        UserScreen(navigateBack = {})
//    }
//}