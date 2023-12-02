package com.mobivone.chargemeter

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.core.content.pm.PackageInfoCompat
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
               getAppVersion(context = LocalContext.current)

                    navigationDrawer(navController =navController)
                    BodyContent(navController = navController,BatteryViewModelInstance)

                }
            }
        }
    }
}


fun getAppVersion(
    context: Context,
): AppVersion? {
    return try {
        val packageManager = context.packageManager
        val packageName = context.packageName
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            packageManager.getPackageInfo(packageName, 0)
        }
        AppVersion(
            versionName = packageInfo.versionName,
            versionNumber = PackageInfoCompat.getLongVersionCode(packageInfo),
        )
    } catch (e: Exception) {
        null
    }
}

data class AppVersion(val versionName: String?, val versionNumber: Long) {

}



