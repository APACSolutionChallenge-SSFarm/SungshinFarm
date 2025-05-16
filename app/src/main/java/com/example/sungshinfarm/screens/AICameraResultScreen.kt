package com.example.sungshinfarm.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sungshinfarm.navigation.Screen

@Composable
fun AICameraResultScreen(
    navController: NavController,
    name: String,
    grade: String,
    price: String
) {
    Column(Modifier.padding(16.dp)) {
        Text("AI 분석 결과", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("품목: $name")
        Text("등급: $grade")
        Text("예상 가격: $price 원")

        Spacer(modifier = Modifier.height(32.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.navigate(Screen.AICamera.route) }) {
                Text("다시 찍기")
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = {
                navController.navigate(
                    Screen.ProductRegister.passData(name, grade, price)
                )
            }) {
                Text("상품 등록")
            }

        }
    }
}
