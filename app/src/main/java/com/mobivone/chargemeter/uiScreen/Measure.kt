package com.mobivone.chargemeter.uiScreen

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.mobivone.chargemeter.BatteryViewModel
import com.mobivone.chargemeter.R
import java.text.DecimalFormat
import java.text.NumberFormat


/** FOR VERTICLE DIVIDER WE NEED MUST SPECIFC HEIGHT FOR CONTAINER (roW,COLOUMN,CARD,BOX,ETC) ***/
@Composable
fun Measure(BatteryViewModel: BatteryViewModel) {
    val plugedd = BatteryViewModel.plugged.collectAsState().value
    var visibility by remember { mutableStateOf(false) }
    visibility = plugedd != "Un Plugged"
    val painter = if (plugedd == "Un Plugged") {
        painterResource(id = R.drawable.unplugged)
    } else {
        painterResource(id = R.drawable.plugged)
    }

    Log.d("WHY VALUE", BatteryViewModel.spotCurrent.collectAsState().value)
    val betteryIcon =
        getPercentageIcon(percentageIconValue = BatteryViewModel.percentageCharge.collectAsState().value)


    //  Toast.makeText(context, "$percentage", Toast.LENGTH_SHORT).show()
    Column(
        Modifier
            .fillMaxSize()
    ) {
        if (visibility) {
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

                    Image(
                        painter = betteryIcon, contentDescription = "", modifier = Modifier
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
                        DegitalLargeValueBox(
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
                        modifier = Modifier.padding(top = 2.dp)
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

                        DegitalLargeValueBox(
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
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(top = 1.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = if(plugedd=="Un Plugged") " - - -"  else plugedd,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily(
                                Font(R.font.digital7mono_b1g5)
                            ), color = colorResource(id = R.color.sky_color)
                        )
                    }
                }
                HorizontalDivider(thickness = 1.dp)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .background(
                            color = colorResource(id = R.color.light_black),
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(top = 7.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_battery_health),
                            contentDescription = "",
                            tint = colorResource(
                                id = R.color.light_white
                            )
                        )
                        Text(
                            text = " ${BatteryViewModel.health.collectAsState().value}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily(
                                Font(R.font.digital7mono_b1g5)
                            ),
                            color = colorResource(id = R.color.current_color)
                        )
                    }
                    VerticalDivider(thickness = 1.dp)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(top = 7.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_battery_type),
                            contentDescription = "",
                            tint = colorResource(
                                id = R.color.light_white
                            )
                        )
                        Text(
                            text = BatteryViewModel.batteryTechnology.collectAsState().value,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily(
                                Font(R.font.digital7mono_b1g5)
                            ), color = colorResource(id = R.color.sky_color)
                        )
                    }

                    VerticalDivider(thickness = 1.dp)
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f).padding(top=5.dp),
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
                            DegitalLargeValueBox(
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

                    DegitalLargeValueBox(
                        largetext = BatteryViewModel.spotCurrent.collectAsState().value,
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
                    DegitalLargeValueBox(
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

                    DegitalSmallValueBox(
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
                    DegitalSmallValueBox(
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
        Spacer(modifier = Modifier.height(10.dp))
        /*** LAST ROW ***/
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.light_black))
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.light_black))
                    .height(50.dp)

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
                    DegitalSmallValueBox(
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
                    DegitalSmallValueBox(
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
            }
            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            /**3rd row complet **/
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
                    DegitalSmallValueBox(
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
                    DegitalSmallValueBox(
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


            }
        }

    }

}

@Composable
fun DegitalLargeValueBox(
    largetext: String,
    largecolor: Color,
    smallcolor: Color,
    smallvalue: String,
    fontsize: TextUnit,
    topPadding: Dp,
    endPadding: Dp,
    fontWeight: FontWeight
) {

    ConstraintLayout() {
        val (largeText, SmallText) = createRefs()

        Text(
            text = "$largetext",
            fontFamily = FontFamily(Font(R.font.digital7mono_b1g5)),
            color = largecolor,
            fontSize = fontsize,
            fontWeight = fontWeight,
            modifier = Modifier.constrainAs(largeText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "$smallvalue",
            color = smallcolor,
            fontSize = 13.sp,
            modifier = Modifier.constrainAs(SmallText) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(largeText.end, margin = 5.dp)
                bottom.linkTo(parent.bottom)
            }
        )


    }
    /*  Box(
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
                  .align(Alignment.BottomStart) // Align the second text to the bottom of the first text
                  .padding(start = 5.dp, bottom = 4.dp) // Add padding to position the second text
          )
      }*/

}


@Composable
fun DegitalSmallValueBox(
    largetext: String,
    largecolor: Color,
    smallcolor: Color,
    smallvalue: String,
    fontsize: TextUnit,
    topPadding: Dp,
    endPadding: Dp,
    fontWeight: FontWeight
) {
    ConstraintLayout() {
        val (largeText, SmallText) = createRefs()

        Text(
            text = "$largetext",
            fontFamily = FontFamily(Font(R.font.digital7mono_b1g5)),
            color = largecolor,
            fontSize = fontsize,
            fontWeight = fontWeight,
            modifier = Modifier.constrainAs(largeText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "$smallvalue",
            color = smallcolor,
            fontSize = 13.sp,
            modifier = Modifier.constrainAs(SmallText) {
                top.linkTo(parent.top, margin = 4.dp)
                start.linkTo(largeText.end, margin = 5.dp)
                bottom.linkTo(parent.bottom)
            }
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