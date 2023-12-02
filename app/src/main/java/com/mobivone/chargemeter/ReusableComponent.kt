package com.mobivone.chargemeter

import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.PackageInfoCompat
import androidx.navigation.NavHostController
import com.mobivone.chargemeter.uiScreen.PrivacyPolicy
import com.mobivone.chargemeter.uiScreen.navigationDrawerItem


@Composable
fun navigationDrawer(navController: NavHostController) {
    val packageManager = LocalContext.current.packageManager
    val packageName = LocalContext.current.packageName
    val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
    } else {
        packageManager.getPackageInfo(packageName, 0)
    }
    val Apkversion=packageInfo.versionName

    //this for Privacy and policy
    val selected = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.app_background))
            .padding(top = 30.dp, start = 20.dp)
    ) {

        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "CHARGE METER",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Battery Meter ",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(top = 6.dp))
            Text(
                text = " v$Apkversion",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )



        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 105.dp)
        ) {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                navigationDrawerItem(
                    "MEASURE",
                    painterResource(id = R.drawable.ic_measure),
                    navController = navController,
                    "measure",
                    selected
                )

                navigationDrawerItem(
                    "DETAIL",
                    painterResource(id = R.drawable.ic_detail_sheet),
                    navController = navController,
                    "detail",
                    selected
                )
                navigationDrawerItem(
                    "ABOUT US",
                    painterResource(id = R.drawable.ic_about_us),
                    navController = navController,
                    "about_us",
                    selected
                )
                PrivacyPolicy(
                    url ="https://mobivone.com/" ,
                   icon= painterResource(id = R.drawable.ic_privacy_policy),
                    title = "PRIVACY POLICY",
                    context=context,
                    selected,


                )
                Spacer(Modifier.height(100.dp))
                navigationDrawerItem(
                    "EXIT",
                    painterResource(id = R.drawable.ic_exit),
                    navController =navController,
                    "exit",
                    selected
                )

            }

        }
    }

}

@Composable
fun CustomExitDailog() {

}