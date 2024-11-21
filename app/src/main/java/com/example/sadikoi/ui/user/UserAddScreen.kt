package com.example.sadikoi.ui.user

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.data.AppContainer
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.data.UsersRepository
import com.example.sadikoi.ui.AppViewModelProvider
import com.example.sadikoi.ui.theme.SaDIKOITheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.sadikoi.R

//@Composable
//fun pickPhoto() {
//
//
//
//}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteUserDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    userName: String
) {

    AlertDialog(
        text = {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.delete_user_confirmation_start))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(userName)
                    }

                    append(stringResource(R.string.delete_user_confirmation_end))
                })
               },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(stringResource(R.string.delete_user_confirmation_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.delete_user_confirmation_cancel))
            }
        }
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
//    navigateBack: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                text = stringResource(R.string.back_confirmation_title)
            )
        },
        text = {
            Text(
                text = stringResource(R.string.back_confirmation_text)
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(stringResource(R.string.back_confirmation_quit))
            }
            },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.back_confirmation_stay))
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserAddScreen(
    context: Context,
    //For Preview
//    viewModel: UserAddViewModel? = null,
//    user: UserAddUiState = viewModel?.userAddUiState ?: UserAddUiState(),
//    onValueChange: (UserDetails) -> Unit = { viewModel?.updateUiState(it) },

    //For real
    viewModel: UserAddViewModel,
    userViewModel: UserViewModel,
    user: UserAddUiState = viewModel.userAddUiState,
    onValueChange: (UserDetails) -> Unit = { viewModel.updateUiState(it) },


    userDetail: UserDetails = user.userDetails,
//    onValueChange: (UserUiState) -> Unit = viewModel::updateUiState, //todo qweeqweq

    navigateBack: (String) -> Unit,
    navigateToRepertoire: () -> Unit,
    backHandle: () -> Unit,
    modifier: Modifier = Modifier
) {

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->


        if (uri != null) {

            Log.d("PhotoPicker", "Selected URI: $uri")
            Log.d("PhotoPicker", "Selected URI: ${uri.toString()}")

            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            Log.d("PhotoPicker", "Selected URI after permission: $uri")
            Log.d("PhotoPicker", "Selected URI after permission: ${uri.toString()}")

            viewModel.updateUiState(userDetail.copy(photoPath = uri.toString()))


        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }



    val coroutineScope = rememberCoroutineScope() //todo pas ici probablement ++ pas de coroutine si lié au viewModel -> viewModelScope !

    var showDialog by remember { mutableStateOf(false) }

    var showBackDialog by remember { mutableStateOf(false) }

    val initialName = remember { userDetail.firstName }

    Log.d("UserAddScreen", "UserAddScreen: $userDetail")

    BackHandler() {
        showBackDialog = true
    }

//    Button(
//        onClick = {
//            coroutineScope.launch {
//                viewModel.saveUser() //todo que faire si lastname or number or firstname isnull -- virer le "?" utile pour la Preview
//                userViewModel.setUserToShow(userDetail.toUser())
//                navigateBack(
//                    if (userDetail.firstName.isNotBlank()) userDetail.firstName else userDetail.number
//                )
//
//            }
//        }
//    ) {
//        Text("Add User")
//    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton( onClick = {
                coroutineScope.launch {
                    viewModel.saveUser() //todo que faire si lastname or number or firstname isnull -- virer le "?" utile pour la Preview
                    userViewModel.setUserToShow(userDetail.toUser())
                    navigateBack(
                        if (userDetail.firstName.isNotBlank()) userDetail.firstName else userDetail.number
                    )

                }
            }
//                onClick = { onSendMessageClicked(
//                    user.id,
//                    user.number,
//                    if (user.firstName.isNotBlank()) user.firstName else user.number
//                ) }
            ) {
                Icon(
                    Icons.Default.Done,
                    contentDescription = "save User"
                )
            }
        }
    ) {

        Column(
//        verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
//            horizontalArrangement = Arrangement.SpaceBetween, //todo comment centrer picture
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
//                .border(1.dp, Color.Blue)
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
                if (userDetail.id != 0) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    UserPicture(
                        uri = userDetail.photoPath,

                        onImageClicked = {
                            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                            //                        SadikoiApplication().contentResolver.takePersistableUriPermission(
                            //                            uri,
                            //                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                            //                        )

                            //                        Log.d("UserAddScreenq", "UserAddScreenq: $uri.")
                        },
                        enabled = true,
                        modifier = Modifier.size(100.dp)
                    )
                    Icon(
                        Icons.Default.Edit,
                        ""
                    )
                }

                if (userDetail.id != 0) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {

                        Button(
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondaryContainer),
                            onClick = {
                                showDialog = true
                                //                        coroutineScope.launch {
                                //                            userViewModel?.deleteUser() //todo virer le "?" utile pour la preview
                                //                            navigateBack()
                                //                        }
                            },
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "send Message",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }

//        Row(
//            horizontalArrangement = Arrangement.End,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            if (userDetail.id != 0) {
//                Button(
//                    onClick = {
//                        coroutineScope.launch {
//                            userViewModel?.deleteUser() //todo virer le "?" utile pour la preview
//                            navigateBack()
//                        }
//                    }
//                ) {
//                    Icon(
//                        Icons.Default.Delete,
//                        contentDescription = "send Message"
//                    )
//                }
//            }
//        }
            if (showBackDialog) {
                BackConfirmationDialog(
                    onConfirm = {
                showBackDialog = false
                navigateBack(initialName)

                                },
                    onDismiss = {
                    showBackDialog = false
                    }
                )
            }

            if (showDialog) {
                DeleteUserDialog(
                    userName = if (userDetail.firstName.isNotBlank()) userDetail.firstName else userDetail.number,
                    onConfirm = {
                        showDialog = false
                        coroutineScope.launch {
                            userViewModel.deleteUser() //todo virer le "?" utile pour la preview
                            navigateToRepertoire() // todo navigateTo RepertoireScreen car UserScreen should not exist anymore for this User

                        }
                    },
                    onDismiss = {
                        showDialog = false
                    }
                )
            }
            BarInfo(barName = stringResource(R.string.last_name), text = {
                TextField( //todo sizemax
                    value = userDetail.lastName,
                    onValueChange = { onValueChange(userDetail.copy(lastName = it)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
//                        .padding(8.dp)
                        .weight(3f)
                )
            })
//            Row(
////            horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
////                .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = stringResource(R.string.last_name),
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
////                    .border(1.dp, Color.Blue)
//
//                )
//                TextField( //todo sizemax
//                    value = userDetail.lastName,
//                    onValueChange = { onValueChange(userDetail.copy(lastName = it)) },
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Next
//                    ),
//                    modifier = Modifier
////                        .padding(8.dp)
//                        .weight(3f)
//                )
//            }

            BarInfo(barName = stringResource(R.string.first_name), text = {
                TextField( //todo sizemax
                    value = userDetail.firstName,
                    onValueChange = { onValueChange(userDetail.copy(firstName = it)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .weight(3f)

                )
            })
//            Row(
////            horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
////                .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = stringResource(R.string.first_name),
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
////                    .border(1.dp, Color.Blue)
//                )
//
//                TextField( //todo sizemax
//                    value = userDetail.firstName,
//                    onValueChange = { onValueChange(userDetail.copy(firstName = it)) },
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Next
//                    ),
//                    modifier = Modifier
//                        .weight(3f)
//
//                )
//
//            }

            BarInfo(barName = stringResource(R.string.number), text = {
                TextField(
                    value = userDetail.number,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { onValueChange(userDetail.copy(number = it)) },
                    modifier = Modifier
                        .weight(3f)
                )
            })

//            Row(
////            horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
////                .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = stringResource(R.string.number),
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
////                    .border(1.dp, Color.Blue)
//                )
//                TextField(
//                    value = userDetail.number,
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Phone,
//                        imeAction = ImeAction.Next
//                    ),
//                    onValueChange = { onValueChange(userDetail.copy(number = it)) },
//                    modifier = Modifier
//                        .weight(3f)
//                )
//
//            }

            BarInfo(barName = stringResource(R.string.mail), text = {
                TextField(
                    value = userDetail.mail,
                    onValueChange = { onValueChange(userDetail.copy(mail = it)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .weight(3f)
                )
            })

//            Row(
////            horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
////                .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = stringResource(R.string.mail),
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
////                    .border(1.dp, Color.Blue)
//                )
//                TextField(
//                    value = userDetail.mail,
//                    onValueChange = { onValueChange(userDetail.copy(mail = it)) },
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Next
//                    ),
//                    modifier = Modifier
//                        .weight(3f)
//                )
//
//            }

            BarInfo(barName = stringResource(R.string.passion), text = {
                TextField(
                    value = userDetail.passion,
                    onValueChange = { onValueChange(userDetail.copy(passion = it)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .weight(3f)
                )
            })

//            Row(
////            horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
////                .border(1.dp, Color.Blue)
//                    .background(color = Color.LightGray)
//                    .height(TextFieldDefaults.MinHeight)
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = stringResource(R.string.passion),
//                    modifier = Modifier
//                        .background(color = Color.Gray)
//                        .weight(1f)
//                        .fillMaxHeight()
//                        .wrapContentHeight()
////                    .border(1.dp, Color.Blue)
//                )
//                TextField(
//                    value = userDetail.passion,
//                    onValueChange = { onValueChange(userDetail.copy(passion = it)) },
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Done
//                    ),
//                    modifier = Modifier
//                        .weight(3f)
//                )
//
//            }
//            Button(
//                onClick = {
//                    coroutineScope.launch {
//                        viewModel.saveUser() //todo que faire si lastname or number or firstname isnull -- virer le "?" utile pour la Preview
//                        userViewModel.setUserToShow(userDetail.toUser())
//                        navigateBack(
//                            if (userDetail.firstName.isNotBlank()) userDetail.firstName else userDetail.number
//                        )
//
//                    }
//                }
//            ) {
//                Text("Add User")
//            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun UserAddScreenPreview(){
//    SaDIKOITheme {
//        UserAddScreen(navigateBack = {})
//    }
//}