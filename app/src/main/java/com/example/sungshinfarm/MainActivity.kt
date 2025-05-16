package com.example.sungshinfarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sungshinfarm.ui.theme.SungshinfarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SungshinfarmTheme {
                SungshinFarmApp()
            }
        }
    }
}
