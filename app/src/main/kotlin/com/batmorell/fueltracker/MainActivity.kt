package com.batmorell.fueltracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.batmorell.fueltracker.screens.FuelTrackingScreen
import com.batmorell.fueltracker.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                FuelTrackingScreen()
            }
        }
    }
}
