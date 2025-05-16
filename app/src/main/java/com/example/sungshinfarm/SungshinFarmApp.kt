package com.example.sungshinfarm

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*

import com.example.sungshinfarm.screens.*
import androidx.navigation.navArgument
import com.example.sungshinfarm.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SungshinFarmApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(
                    Screen.ProductRegister,
                    Screen.StoreManagement,
                    Screen.AICamera
                ).forEach { screen ->
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(screen.route) },
                        label = {
                            Text(
                                when (screen) {
                                    is Screen.ProductRegister -> "상품 등록"
                                    is Screen.StoreManagement -> "상점 관리"
                                    is Screen.AICamera -> "AI 카메라"
                                    else -> ""
                                }
                            )
                        },
                        icon = {}
                    )
                }
            }

        }
    ) {
        NavHost(navController, startDestination = Screen.AICamera.route) {

            composable(Screen.ProductRegister.route,
                arguments = listOf(
                    navArgument("name") { defaultValue = "" },
                    navArgument("grade") { defaultValue = "" },
                    navArgument("price") { defaultValue = "" }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val grade = backStackEntry.arguments?.getString("grade") ?: ""
                val price = backStackEntry.arguments?.getString("price") ?: ""
                ProductRegisterScreen(navController, name, grade, price)

            }

            composable(Screen.StoreManagement.route) {
                StoreManagementScreen()
            }

            composable(Screen.AICamera.route) {
                AICameraScreen(navController)
            }


            composable(
                Screen.AICameraResult.route,
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("grade") { type = NavType.StringType },
                    navArgument("price") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val grade = backStackEntry.arguments?.getString("grade") ?: ""
                val price = backStackEntry.arguments?.getString("price") ?: ""
                AICameraResultScreen(navController, name, grade, price)
            }

            composable(Screen.RegisterComplete.route) {
                RegisterCompleteScreen()
            }
        }
    }
}
