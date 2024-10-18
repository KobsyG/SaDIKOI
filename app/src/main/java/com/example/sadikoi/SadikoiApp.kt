package com.example.sadikoi

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sadikoi.ui.HomeScreen
import com.example.sadikoi.ui.theme.SaDIKOITheme

@Composable
fun SadikoiApp(
    //todo ViewModel
    //todo navController
) {
    Scaffold(
        topBar = {
            SadikoiAppBar(
                modifier = Modifier
                    .height(40.dp)
                    .border(1.dp, Color.Blue)
            )
        } //todo topBar
    ) { innerPadding ->
        //todo NavHost - probably Modifier.padding(innerPadding) dans NavHost
        //todo composable(route =
        HomeScreen(
            modifier = Modifier
                .padding(innerPadding) //todo probably not innerPadding
                .fillMaxSize()
                .padding(16.dp)
                .border(1.dp, Color.Red)
        )


    }
}

@Composable
fun SadikoiAppBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text("Logo") //todo
        Text("SaDIKOI")
        Text("Menu")
    }
}

@Preview(showBackground = true)
@Composable
fun SadikoiAppPreview(){
    SaDIKOITheme {
        SadikoiApp()
    }
}