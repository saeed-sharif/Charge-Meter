package com.mobivone.chargemeter.uiScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobivone.chargemeter.BatteryViewModel
import com.mobivone.chargemeter.R
import java.text.DecimalFormat
import java.text.NumberFormat


/** FOR VERTICLE DIVIDER WE NEED MUST SPECIFC HEIGHT FOR CONTAINER (roW,COLOUMN,CARD,BOX,ETC) ***/
@Composable
fun Measure() {
    var MeasureFormat: NumberFormat = DecimalFormat("0.00")
    val context = LocalContext.current


    val BatteryViewModel = BatteryViewModel.getInstance(context)
    val plugedd= BatteryViewModel.plugged.collectAsState().value
    var visibility by remember{ mutableStateOf(false)}
     if (plugedd=="Unknown"){
         visibility=false
     }
    else{
        visibility=true
    }


    Log.d("WHY VALUE", BatteryViewModel.spotCurrent.collectAsState().value)
    val betteryIcon = getPercentageIcon(percentageIconValue =  BatteryViewModel.percentageCharge.collectAsState().value)


    //  Toast.makeText(context, "$percentage", Toast.LENGTH_SHORT).show()
    Column(
        Modifier
            .fillMaxSize()
    ) {
        if(visibility){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_energy),
                contentDescription = "",
                tint = colorResource(
                    id = R.color.sky_color
                )
            )
            Spacer(modifier = Modifier.width(9.dp))
            Text(
                text = "NORMAL ",
                fontSize = 16.sp,
                color = colorResource(id = R.color.sky_color)
            )
            Text(
                text = "${BatteryViewModel.plugged.collectAsState().value}",
                fontSize = 16.sp,
                color = colorResource(id = R.color.sky_color)
            )
        }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 1.dp, bottom = 10.dp)
                .background(
                    color = colorResource(id = R.color.light_black),
                    shape = RoundedCornerShape(0.dp)
                )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 15.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${BatteryViewModel.percentageCharge.collectAsState().value}",
                        fontSize = 60.sp,
                        fontFamily = FontFamily(Font(R.font.digital7_1e1z)),
                        color = colorResource(id = R.color.white_text_color)
                    )
                    Box(
                        Modifier.height(45.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = "%",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }


                    Icon(
                        painter = betteryIcon,
                        contentDescription = "",
                        modifier = Modifier
                            .height(70.dp)
                            .width(160.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(0.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_voltage),
                            contentDescription = "",
                            tint = colorResource(id = R.color.light_white)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        /* Text(
                         text = "0.00",
                         fontFamily = FontFamily(Font(R.font.digital7mono_b1g5)),
                         color = colorResource(id = R.color.sky_color),
                         fontSize = 28.sp,)*/
                        DegitalValueBox(
                            largetext = " ${BatteryViewModel.batteryVoltage.collectAsState().value} V",
                            largecolor = colorResource(id = R.color.sky_color),
                            smallcolor = colorResource(id = R.color.white_text_color),
                            smallvalue = "",
                            fontsize = 28.sp,
                            topPadding = 5.dp,
                            endPadding = 10.dp,
                            fontWeight = FontWeight.W600
                        )
                    }
                    VerticalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_temperature),
                            contentDescription = "",
                            tint = colorResource(id = R.color.light_white)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        DegitalValueBox(
                            largetext = "${BatteryViewModel.BatteryTemprature.collectAsState().value}",
                            largecolor = colorResource(id = R.color.sky_color),
                            smallcolor = colorResource(id = R.color.white_text_color),
                            smallvalue = "",
                            fontsize = 28.sp,
                            topPadding = 5.dp,
                            endPadding = 10.dp,
                            fontWeight = FontWeight.W600
                        )
                    }
                    VerticalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_capacity),
                            contentDescription = "",
                            tint = colorResource(id = R.color.light_white)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DegitalValueBox(
                                largetext = "${BatteryViewModel.batteryCapacity.collectAsState().value}",
                                largecolor = colorResource(id = R.color.current_color),
                                smallcolor = colorResource(id = R.color.white_text_color),
                                smallvalue = "mAh",
                                fontsize = 28.sp,
                                topPadding = 5.dp,
                                endPadding = 10.dp,
                                fontWeight = FontWeight.W600
                            )
                        }
                    }
                }
            }
        }
        //3Rd Card
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.light_black))
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(77.dp)
                    .padding(0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "CURRENT",
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.white_text_color)
                    )

                    DegitalValueBox(
                        largetext = "${BatteryViewModel.spotCurrent.collectAsState().value}",
                        largecolor = colorResource(id = R.color.current_color),
                        smallcolor = colorResource(id = R.color.white_text_color),
                        smallvalue = "A",
                        fontsize = 40.sp,
                        topPadding = 14.dp,
                        endPadding = 45.dp,
                        fontWeight = FontWeight.W600
                    )


                }
                VerticalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 0.dp)
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = "POWER",
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.white_text_color)
                    )
                    DegitalValueBox(
                        largetext = "${BatteryViewModel.WattPower.collectAsState().value}",
                        largecolor = colorResource(id = R.color.sky_color),
                        smallcolor = colorResource(id = R.color.white_text_color),
                        smallvalue = "W",
                        fontsize = 40.sp,
                        topPadding = 14.dp,
                        endPadding = 44.dp,
                        fontWeight = FontWeight.W600
                    )
                }


            }
            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    DegitalValueBox(
                        largetext = "0.00",
                        largecolor = colorResource(id = R.color.current_color),
                        smallcolor = colorResource(id = R.color.white_text_color),
                        smallvalue = "Ah",
                        fontsize = 30.sp,
                        topPadding = 10.dp,
                        endPadding = 47.dp,
                        fontWeight = FontWeight.W600
                    )
                }
                VerticalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 0.dp, bottom = 7.dp)
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    DegitalValueBox(
                        largetext = "0.00",
                        largecolor = colorResource(id = R.color.sky_color),
                        smallcolor = colorResource(id = R.color.white_text_color),
                        smallvalue = "Wh",
                        fontsize = 30.sp,
                        topPadding = 10.dp,
                        endPadding = 45.dp,
                        fontWeight = FontWeight.W600
                    )
                }

            }
        }
        /*** LAST ROW ***/
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = colorResource(id = R.color.light_black))
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Max",
                    color = colorResource(id = R.color.white_text_color),
                    fontSize = 13.sp
                )
                DegitalValueBox(
                    largetext = "${BatteryViewModel.maximumCurrent.collectAsState().value}",
                    largecolor = colorResource(id = R.color.sky_color),
                    smallcolor = colorResource(id = R.color.white_text_color),
                    smallvalue = "A",
                    fontsize = 20.sp,
                    topPadding = 2.dp,
                    endPadding = 32.dp,
                    fontWeight = FontWeight.W600
                )

            }
            VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(top = 10.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "MIN",
                    color = colorResource(id = R.color.white_text_color),
                    fontSize = 13.sp
                )
                DegitalValueBox(
                    largetext = "${BatteryViewModel.minimumCurrent.collectAsState().value}",
                    largecolor = colorResource(id = R.color.sky_color),
                    smallcolor = colorResource(id = R.color.white_text_color),
                    smallvalue = "A",
                    fontsize = 20.sp,
                    topPadding = 2.dp,
                    endPadding = 32.dp,
                    fontWeight = FontWeight.W600
                )

            }
            VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(top = 10.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "AVG",
                    color = colorResource(id = R.color.white_text_color),
                    fontSize = 13.sp
                )
                DegitalValueBox(
                    largetext = "${BatteryViewModel.MeasureAvrageCurrent.collectAsState().value}",
                    largecolor = colorResource(id = R.color.sky_color),
                    smallcolor = colorResource(id = R.color.white_text_color),
                    smallvalue = "A",
                    fontsize = 20.sp,
                    topPadding = 2.dp,
                    endPadding = 32.dp,
                    fontWeight = FontWeight.W600
                )


            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        /**3rd row complet **/
        Row(
            Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = colorResource(id = R.color.light_black))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(3.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DegitalValueBox(
                    largetext = "${BatteryViewModel.maximumWatt.collectAsState().value}",
                    largecolor = colorResource(id = R.color.sky_color),
                    smallcolor = colorResource(id = R.color.white_text_color),
                    smallvalue = "W",
                    fontsize = 20.sp,
                    topPadding = 2.dp,
                    endPadding = 26.dp,
                    fontWeight = FontWeight.W600
                )

            }
            VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(bottom = 8.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(3.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DegitalValueBox(
                    largetext = "${BatteryViewModel.minimumWatt.collectAsState().value}",
                    largecolor = colorResource(id = R.color.sky_color),
                    smallcolor = colorResource(id = R.color.white_text_color),
                    smallvalue = "W",
                    fontsize = 20.sp,
                    topPadding = 2.dp,
                    endPadding = 26.dp,
                    fontWeight = FontWeight.W600
                )
            }
            VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(bottom = 8.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(3.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DegitalValueBox(
                    largetext = "${BatteryViewModel.MeasureaverageWatt.collectAsState().value}",
                    largecolor = colorResource(id = R.color.sky_color),
                    smallcolor = colorResource(id = R.color.white_text_color),
                    smallvalue = "W",
                    fontsize = 20.sp,
                    topPadding = 2.dp,
                    endPadding = 26.dp,
                    fontWeight = FontWeight.W600
                )

            }


        }

    }

}

@Composable
fun DegitalValueBox(
    largetext: String,
    largecolor: Color,
    smallcolor: Color,
    smallvalue: String,
    fontsize: TextUnit,
    topPadding: Dp,
    endPadding: Dp,
    fontWeight: FontWeight
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$largetext",
            fontFamily = FontFamily(Font(R.font.digital7mono_b1g5)),
            color = largecolor,
            fontSize = fontsize,
            fontWeight = fontWeight
        )
        Text(
            text = "$smallvalue",
            color = smallcolor,
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = topPadding, end = endPadding)
        )
    }

}


@Composable
fun getPercentageIcon(percentageIconValue: Int): Painter {
    val batteryIcon = when {
        percentageIconValue <= 5 -> painterResource(id = R.drawable.ic_empty_battery)
        percentageIconValue > 5 && percentageIconValue <= 20 -> painterResource(id = R.drawable.ic_twentyfive_battery)
        percentageIconValue > 20 && percentageIconValue <= 50 -> painterResource(id = R.drawable.ic_fifty_battery)
        percentageIconValue > 50 && percentageIconValue <= 75 -> painterResource(id = R.drawable.ic_seventyfive_battery)
        else -> painterResource(id = R.drawable.ic_full_battery)
    }
    return batteryIcon
    //  return  rememberVectorPainter(image = batteryIcon)
}







