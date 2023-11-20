package com.mobivone.chargemeter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobivone.chargemeter.uiScreen.BodyContent

@OptIn(ExperimentalMaterial3Api::class)
/*
@Composable
fun CostomAppbar(title: String, onClick: () -> Unit) {

}
*/

@Composable
fun navigationDrawer(navController: NavHostController) {
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
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Battery Meter",
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
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(30.dp)) {
                navigationDrawerItem(
                    "MEASURE",
                    painterResource(id = R.drawable.ic_measure),
                    navController = navController,
                    "measure"
                )

                navigationDrawerItem(
                    "DETAIL",
                    painterResource(id = R.drawable.ic_detail_sheet),
                    navController = navController,
                    "detail"
                )
                navigationDrawerItem(
                    "ABOUT US",
                    painterResource(id = R.drawable.ic_about_us),
                    navController = navController,
                    "about_us"
                )
                Spacer(Modifier.height(100.dp))
                navigationDrawerItem(
                    "EXIT",
                    painterResource(id = R.drawable.ic_exit),
                    navController = navController,
                    "exist"
                )

            }

        }
    }

}

@Composable
fun navigationDrawerItem(
    title: String,
    icon: Painter,
    navController: NavHostController,
    destination: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoot = navBackStackEntry?.destination?.route
    val selected = currentRoot == destination
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(destination) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = if (selected) colorResource(id = R.color.sky_color) else Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "$title",
            color = if (selected) colorResource(id = R.color.sky_color) else Color.White
        )
    }
}



@Composable
@Preview(showBackground = true)
fun Preview() {
    BodyContent(rememberNavController())
}