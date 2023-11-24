package com.mobivone.chargemeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobivone.chargemeter.ui.theme.ChargeMeterTheme
import com.mobivone.chargemeter.uiScreen.BodyContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val BatteryViewModelInstance=BatteryViewModel.getInstance(this)
        setContent {
            val navController:NavHostController= rememberNavController()
            ChargeMeterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.app_background)
                ) {
                    navigationDrawer(navController =navController)
                    BodyContent(navController = navController,BatteryViewModelInstance)

                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChargeMeterTheme {
        Measure(batteryData)
    }
}*/


