package com.example.sungshinfarm.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sungshinfarm.model.PredictionResult
import com.example.sungshinfarm.navigation.Screen
import com.example.sungshinfarm.network.uploadImageToFastAPI
import kotlinx.coroutines.*

@Composable
fun AICameraScreen(navController: NavController) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val stream = context.contentResolver.openInputStream(uri)
            val bytes = stream?.readBytes()
            stream?.close()

            bytes?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = uploadImageToFastAPI(it)
                    withContext(Dispatchers.Main) {
                        navController.navigate(
                            Screen.AICameraResult.passData(
                                result.name,
                                result.qualityGrade,
                                result.recommendedPrice.toString()
                            )
                        )
                    }
                }
            }
        }
    }

    Column(Modifier.padding(16.dp)) {
        Text("AI Camera 화면", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { launcher.launch("image/*") }) {
            Text("사진 선택하기")
        }
    }
}
