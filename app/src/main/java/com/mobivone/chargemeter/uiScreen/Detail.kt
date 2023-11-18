package com.mobivone.chargemeter.uiScreen


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobivone.chargemeter.R

@Composable
fun Detail() {
    Column(
        Modifier
            .fillMaxSize().padding(5.dp)
            .background(color = colorResource(id = R.color.app_background)).clip(shape= RoundedCornerShape(5.dp)),


    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.light_black))
                .padding(top = 10.dp, start = 24.dp)
        ) {
            Text(
                text = "Phone Model:",
                color = colorResource(id = R.color.white_text_color),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(95.dp)
                .background(color = colorResource(id = R.color.light_black),shape = RoundedCornerShape(5.dp))
                .padding(top = 20.dp, start = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth().weight(1f),
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
                    text = " HEALTH",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily(
                        Font(R.font.digital7mono_b1g5)
                    ),
                    color = colorResource(id = R.color.current_color)
                )
            }
            VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
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
                    text = " LI ION",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily(
                        Font(R.font.digital7mono_b1g5)
                    ), color = colorResource(id = R.color.sky_color)
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier
                .background(colorResource(id = R.color.light_black), shape = RoundedCornerShape(5.dp))

        ) {

            Column(Modifier.fillMaxWidth().padding(10.dp), verticalArrangement =Arrangement.spacedBy(10.dp)) {
                Spacer(modifier = Modifier.height(4.dp))
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_battery_level),
                    "Battery Level",
                    "35 %"
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_plugged),
                    "Plugged",
                    "li ion"
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_status),
                    "Status",
                    "li ion"
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_fast_charging),
                    "Fast Charging",
                    ""
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_temperature),
                    "Temprature",
                    "li ion"
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_capacity),
                    "Battery Capacity",
                    ""
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_energy),
                    "Battery Energy",
                    ""
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_current_charging),
                    "Average Current",
                    ""
                )
                minorDetailBatter(
                    painterResource(id = R.drawable.ic_charging_current),
                    "Average Power",
                    ""
                )



            }
        }


    }

}


@Composable
fun minorDetailBatter(icon: Painter, name: String, specs: String) {
    Row(
        Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = colorResource(id = R.color.light_white)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "$name", color = colorResource(id = R.color.light_white), fontSize = 16.sp)
        Text(
            text = "$specs",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 7.dp),
            textAlign = TextAlign.End,
            color = colorResource(id = R.color.light_white)
        )

    }

}

