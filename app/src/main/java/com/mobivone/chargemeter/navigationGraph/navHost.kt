package com.mobivone.chargemeter.navigationGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobivone.chargemeter.uiScreen.AboutUs
import com.mobivone.chargemeter.uiScreen.Detail
import com.mobivone.chargemeter.uiScreen.Measure

@Composable
fun NavGraph(navController: NavHostController, ) {

    NavHost(navController = navController, startDestination = "measure") {
        composable("measure") {
            Measure()
        }
        composable("detail") {
            Detail()
        }
        composable("about_us") {

            AboutUs()
        }


    }
}
