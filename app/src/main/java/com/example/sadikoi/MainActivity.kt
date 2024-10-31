package com.example.sadikoi

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sadikoi.ui.theme.SaDIKOITheme

class MainActivity : AppCompatActivity() {

//    val receiveSmsPermission = rememberPermissionState(android.Manifest.permission.RECEIVE_SMS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() //todo ca degage ??

//        val requestPermissionLauncher =
//            registerForActivityResult(ActivityResultContracts.RequestPermission()
//            ) {
//                isGranted: Boolean ->
//                if (isGranted) {
//                    setContent {
//                        SaDIKOITheme {
//                            SadikoiApp() //todo check Flow plus serieusement - check companion object en Kotlin - check @Volatile
//                        }                //todo check content.Context
//                    }
//                } else {
//                    requestPermissionLauncher
//                }
//            }

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECEIVE_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            setContent {
                SaDIKOITheme {
                    SadikoiApp() //todo check Flow plus serieusement - check companion object en Kotlin - check @Volatile
                }                //todo check content.Context
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.RECEIVE_SMS),
                REQUEST_CODE
            )
        }
    }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (requestCode == REQUEST_CODE) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission accordée, lancer le contenu
                    setContent {
                        SaDIKOITheme {
                            SadikoiApp()
                        }
                    }
                } else {
                    // Permission refusée, gérer en conséquence
                    showPermissionDeniedMessage()
                }
            }
        }




    private fun showPermissionDeniedMessage() {
        Toast.makeText(this, "La permission est nécessaire pour utiliser cette fonctionnalité.", Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val REQUEST_CODE = 101
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SaDIKOITheme {
//        Greeting("Android")
//    }
//}
