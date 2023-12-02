package com.mobivone.chargemeter.uiScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mobivone.chargemeter.BatteryViewModel
import com.mobivone.chargemeter.R
import com.mobivone.chargemeter.navigationGraph.NavGraph

var navigateClick = mutableStateOf(false)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(navController: NavHostController, BatteryViewModelInstance: BatteryViewModel) {


    var appbarTitle by remember {
        mutableStateOf("")
    }
    val offSetAnim by animateDpAsState(targetValue = if (navigateClick.value) 253.dp else 0.dp)
    val scaleAnim by animateFloatAsState(targetValue = if (navigateClick.value) 0.6f else 1.0f)
    //to track which screen are now visible Right now
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(currentRoute) {
        navigateClick.value =
            false // Reset the navigateClick state when a new route is navigated to
    }
    if (currentRoute == "measure") {
        appbarTitle = "MEASURE"

    } else if (currentRoute == "detail") {
        appbarTitle = "DETAIL"

    } else if (currentRoute == "about_us") {
        appbarTitle = "ABOUT US"

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scale(scaleAnim)
            .offset(x = offSetAnim)
        /* .clip(if (navigateClick) RoundedCornerShape(0.dp) else RoundedCornerShape(0.dp))*/
        //  .verticalScroll(rememberScrollState())
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.dp, color = colorResource(id = R.color.light_black)),

            containerColor = colorResource(id = R.color.app_background),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = appbarTitle,
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = colorResource(
                                id = R.color.white
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        val icon =
                            if (navigateClick.value) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu
                        IconButton(
                            onClick = { navigateClick.value = !navigateClick.value },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color.Black // Set the color of the icon to black
                            )
                        ) {
                            Icon(icon, contentDescription = null, tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = colorResource(
                            id = R.color.light_black
                        )
                    )
                )
                /*CostomAppbar("CustomAppBar", onClick = { navigateClick = !navigateClick })*/
            },
            content = { paddingvalue ->
                Column(Modifier.padding(paddingvalue)) {

                    NavGraph(navController = navController, BatteryViewModelInstance)
                }

            }
        )
    }
}


@Composable
fun navigationDrawerItem(
    title: String,
    icon: Painter,
    navController: NavHostController,
    destination: String,
    selected: MutableState<Boolean>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoot = navBackStackEntry?.destination?.route
    selected.value = currentRoot == destination

    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(destination)
                navigateClick.value = false
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = if (selected.value) colorResource(id = R.color.sky_color) else Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "$title",
            color = if (selected.value) colorResource(id = R.color.sky_color) else Color.White
        )
    }
}

@Composable
fun PrivacyPolicy(
    url: String,
    icon: Painter,
    title: String,
    context: Context,
    selected: MutableState<Boolean>,
) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                navigateClick.value = false
            /*    selected.value=false*/
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = if (selected.value) colorResource(id = R.color.sky_color) else Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "$title",
            color = if (selected.value) colorResource(id = R.color.sky_color) else Color.White
        )
    }

}


