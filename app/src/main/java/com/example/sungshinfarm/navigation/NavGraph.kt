package com.example.sungshinfarm.navigation

sealed class Screen(val route: String) {
    object ProductRegister : Screen("product_register?name={name}&grade={grade}&price={price}") {
        fun passData(name: String, grade: String, price: String): String =
            "product_register?name=$name&grade=$grade&price=$price"
    }

    object StoreManagement : Screen("store_management")
    object AICamera : Screen("ai_camera")

    object AICameraResult : Screen("ai_camera_result/{name}/{grade}/{price}") {
        fun passData(name: String, grade: String, price: String) =
            "ai_camera_result/$name/$grade/$price"
    }

    object RegisterComplete : Screen("register_complete")
}
