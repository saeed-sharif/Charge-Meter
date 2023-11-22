package com.mobivone.chargemeter.uiScreen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mobivone.chargemeter.R
import com.mobivone.chargemeter.navigationGraph.NavGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(navController: NavHostController) {


    var appbarTitle by remember {
        mutableStateOf("")
    }
    var navigateClick by remember { mutableStateOf(false) }
    val offSetAnim by animateDpAsState(targetValue = if (navigateClick) 253.dp else 0.dp)
    val scaleAnim by animateFloatAsState(targetValue = if (navigateClick) 0.6f else 1.0f)
    //to track which screen are now visible Right now
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        navigateClick = false // Reset the navigateClick state when a new route is navigated to
    }

    if (currentRoute == "measure") {
        appbarTitle = "Measure"

    } else if (currentRoute == "detail") {
        appbarTitle = "Detail"

    } else if (currentRoute == "about_us") {
        appbarTitle = "About Us"

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
                            if (navigateClick) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu
                        IconButton(
                            onClick = { navigateClick = !navigateClick },
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

                        NavGraph(navController = navController)



                }

            }
        )
    }
}