package com.example.sungshinfarm.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.sungshinfarm.navigation.Screen


@Composable
fun ProductRegisterScreen(
    navController: NavController,
    name: String,
    grade: String,
    price: String
) {
    var itemName by remember { mutableStateOf(name) }
    var quality by remember { mutableStateOf(grade) }
    var priceValue by remember { mutableStateOf(price) }

    Column(Modifier.padding(16.dp)) {
        Text("상품 등록", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("이름") }
        )

        OutlinedTextField(
            value = quality,
            onValueChange = { quality = it },
            label = { Text("등급") }
        )

        OutlinedTextField(
            value = priceValue,
            onValueChange = { priceValue = it },
            label = { Text("예상 가격") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(Screen.RegisterComplete.route)
        }) {
            Text("등록하기")
        }


    }
}
