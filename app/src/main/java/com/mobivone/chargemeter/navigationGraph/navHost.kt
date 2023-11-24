package com.mobivone.chargemeter.navigationGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobivone.chargemeter.BatteryViewModel
import com.mobivone.chargemeter.uiScreen.AboutUs
import com.mobivone.chargemeter.uiScreen.Detail
import com.mobivone.chargemeter.uiScreen.Measure

@Composable
fun NavGraph(navController: NavHostController, BatteryViewModelInstance: BatteryViewModel, ) {

    NavHost(navController = navController, startDestination = "measure") {
        composable("measure") {
            Measure(BatteryViewModelInstance)
        }
        composable("detail") {
            Detail(BatteryViewModelInstance)
        }
        composable("about_us") {

            AboutUs()
        }


    }
}
