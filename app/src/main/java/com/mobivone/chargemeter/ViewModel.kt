package com.mobivone.chargemeter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.ViewModel
import com.mobivone.chargemeter.utils.BatteryManagerBroadcastReceiver
import com.mobivone.chargemeter.utils.batteryData
import com.mobivone.chargemeter.utils.batteryUtils
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.DecimalFormat
import java.text.NumberFormat

class BatteryViewModel(private val context: Context) : ViewModel() {

    var format: NumberFormat = DecimalFormat("#.#")
    var MeasureFormat:NumberFormat=DecimalFormat("0.00")


    val batteryManager: BatteryManager by lazy {
        context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    }

    var batteryDataFlow: MutableStateFlow<batteryData> = MutableStateFlow(
        batteryData(
            0,
            "",
            "",
            "",
            status = "",
            batteryVoltage = 4.0f,
            batteryTemprature = "0",
            batteryAvrageCurrent = 1,
            batteryCapacity = 4000,
            batteryEnergy = 432,
            "slow",
            "",
            avragePower =4.4f,
            batteryMeasureAverageCurrent =0.91f,
            spotCurrent ="43.3f",
            averageWatt =3.8,

            )
    )

    val broadcastReceiver: BroadcastReceiver = BatteryManagerBroadcastReceiver {
        val health =
            batteryUtils.getBatteryHealthText(it.getIntExtra(BatteryManager.EXTRA_HEALTH, -99))
        var percentageCharge: Int = it.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)

        val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, -99)
        val scale = it.getIntExtra(BatteryManager.EXTRA_SCALE, -99)
        val status =
            batteryUtils.getBatteryStatusText(it.getIntExtra(BatteryManager.EXTRA_STATUS, -99))
        val plugged =
            batteryUtils.getBatteryPluggedText(it.getIntExtra(BatteryManager.EXTRA_PLUGGED, -99))
        val batteryTechnology = it.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
        val batteryVoltage =
            it.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -99)
        val formatedBatteryVoltage = format.format(batteryVoltage * 0.001).toFloat()
        val Temprature = it.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -99) / 10
        val batteryTemprature = Temprature.toString() + " " + 0x00B0.toChar() + "C"

        val batteryAvrageCurrent =
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE) / (1000)
        val batteryMeasureAverageCurrent=batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE)/1_000_000

        val avgWatt = batteryAvrageCurrent * formatedBatteryVoltage.toFloat()
        val formatedAvrageWatt =format.format(avgWatt)
        //charging speed
        val spotCurrentForBatteryCharge =
            Math.abs(batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW) / (1000))
        val batterycharge = spotCurrentForBatteryCharge / 1000.0

        val watt = batterycharge * formatedBatteryVoltage.toFloat()
        val chargingSpeed = batteryUtils.getChargingSpeed(watt)
        val batteryCapacity = batteryUtils.getBatteryCapacity(context).toInt()
        val batteryEnergy = batteryVoltage.toInt() * batteryCapacity.toInt()
        val averagePower=batteryAvrageCurrent * formatedBatteryVoltage.toFloat()
        val spotCurrentt = MeasureFormat.format(Math.abs(batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW) / (1000.0 * 1000.0)))
        val averageCurrentformeasure=batteryUtils.getAvgCurrent(batteryManager)

        val averageWatt= averageCurrentformeasure * formatedBatteryVoltage

        val newDataList = batteryData(
            percentageCharge,
            health,
            batteryTechnology,
            plugged,
            status,
            formatedBatteryVoltage,
            batteryTemprature,
            batteryAvrageCurrent = batteryAvrageCurrent,
            batteryCapacity,
            batteryEnergy,
            chargingSpeed,
            formatedAvrageWatt,
            averagePower,
            batteryMeasureAverageCurrent.toFloat(),
            spotCurrentt,
            averageWatt

        )
        batteryDataFlow.value = newDataList
    }

    companion object {
        @Volatile
        private lateinit var instance: BatteryViewModel
        fun getInstance(context: Context): BatteryViewModel {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = BatteryViewModel(context)
                }
                return instance
            }
        }
    }

    init {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(broadcastReceiver, filter)
    }
}
