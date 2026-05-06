package com.example.mobileshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobileshopapp.ui.navigation.ShopNavHost
import com.example.mobileshopapp.ui.theme.MobileShopAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileShopAppTheme {
                ShopNavHost()
            }
        }
    }
}
