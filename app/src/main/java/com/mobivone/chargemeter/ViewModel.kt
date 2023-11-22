package com.mobivone.chargemeter

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.mobivone.chargemeter.utils.BatteryManagerBroadcastReceiver
import com.mobivone.chargemeter.utils.batteryUtils
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.DecimalFormat
import java.text.NumberFormat

class BatteryViewModel(private val context: Context) : ViewModel() {
    var format: NumberFormat = DecimalFormat("#.#")
    var MeasureFormat: NumberFormat = DecimalFormat("0.00")
    val batteryManager: BatteryManager by lazy {
        context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    }
    val health = MutableStateFlow("")
    val batteryTechnology = MutableStateFlow("")
    val percentageCharge = MutableStateFlow(0)
    val plugged = MutableStateFlow("")
    val status = MutableStateFlow("")
    val batteryVoltage = MutableStateFlow(0.0f)
    val chargingSpeed = MutableStateFlow("")
    val BatteryTemprature = MutableStateFlow("")
    val batteryCapacity = MutableStateFlow(0)
    val batteryEnergy = MutableStateFlow(0)
    val batteryAvrageCurrent = MutableStateFlow(0)
    val averagePower = MutableStateFlow("")
    val MeasureAvrageCurrent = MutableStateFlow("0.0")
    val MeasureaverageWatt = MutableStateFlow("0.0")

    /*** This for Measure Activity  ***/
    val spotCurrent = MutableStateFlow("")
    val WattPower = MutableStateFlow("")

    /******** MIN MAX, CURRENT & WATT ***********/
    var maximumCurrent = MutableStateFlow("")
    var minimumCurrent = MutableStateFlow("")
    var maximumWatt = MutableStateFlow("")
    var minimumWatt = MutableStateFlow("")

    @SuppressLint("RememberReturnType")
    val broadcastReceiver: BroadcastReceiver = BatteryManagerBroadcastReceiver {
        //Detail  View related data
        health.value =
            batteryUtils.getBatteryHealthText(it.getIntExtra(BatteryManager.EXTRA_HEALTH, -99))
        batteryTechnology.value = it.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY).toString()
        percentageCharge.value = it.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
        plugged.value =
            batteryUtils.getBatteryPluggedText(it.getIntExtra(BatteryManager.EXTRA_PLUGGED, -99))
        status.value =
            batteryUtils.getBatteryStatusText(it.getIntExtra(BatteryManager.EXTRA_STATUS, -99))
        //Full Voltage
        batteryVoltage.value = batteryUtils.getVoltage(it)
        /*** For finding Charging Speed **/
        val spotCurrentForBatteryCharge =
            Math.abs(batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW) / (1000))
        val batterycharge = spotCurrentForBatteryCharge / 1000.0
        val watt = batterycharge * batteryVoltage.value
        chargingSpeed.value = batteryUtils.getChargingSpeed(watt)
        /*** Speed End **/
        val Temprature = it.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -99) / 10
        BatteryTemprature.value = Temprature.toString() + " " + 0x00B0.toChar() + "C"
        batteryCapacity.value = batteryUtils.getBatteryCapacity(context).toInt()
        batteryEnergy.value =
            batteryUtils.AveragePower(batteryManager, batteryVoltage.value, batteryCapacity.value)
        /*** AVERAGE CURRENT NOT GOT IT WHEN CHANG WE NEED TO REQUEST MULTIPLE TIME ***/
        val handler: Handler = Handler()
        val AverageCurrentChecker = object : Runnable {
            var maximum = 0.0
            var minimum = 0.0
            override fun run() {
                try {
                    batteryAvrageCurrent.value = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE) / 1000
                    val averageWatt = batteryAvrageCurrent.value * batteryVoltage.value.toDouble()
                    averagePower.value = format.format(averageWatt)
                    /****    SPOT CURRENT AND  WATT POWER *****/
                    val spotCurrentt = batteryUtils.spotCurrent(batteryManager)
                    spotCurrent.value = ""
                    spotCurrent.value = MeasureFormat.format(spotCurrentt)
                    val wattpower = spotCurrent.value.toDouble() * batteryVoltage.value.toFloat()
                    WattPower.value = ""
                    WattPower.value = MeasureFormat.format(wattpower)
                    /******************   Measure Average Current and Power *****************************/
                    MeasureAvrageCurrent.value =
                        MeasureFormat.format(batteryUtils.getAvgCurrent(batteryManager))
                    MeasureaverageWatt.value = MeasureFormat.format(MeasureAvrageCurrent.value.toDouble() * batteryVoltage.value)
                    /*** FOR MIN MAX  ***/
                    if (maximum == 0.0 || spotCurrentt > maximum) {
                        maximum = spotCurrentt
                        maximumCurrent.value = ""
                        maximumCurrent.value = MeasureFormat.format(spotCurrentt)
                        val maxWatt = spotCurrentt * batteryVoltage.value
                        maximumWatt.value = ""
                        maximumWatt.value = MeasureFormat.format(maxWatt)
                    }
                    if (minimum == 0.0 || spotCurrentt < minimum) {
                        minimum = spotCurrentt
                        minimumCurrent.value = ""
                        minimumCurrent.value = MeasureFormat.format(spotCurrentt)
                        val minWatt = spotCurrentt * batteryVoltage.value
                        minimumWatt.value = ""
                        minimumWatt.value = MeasureFormat.format(minWatt)
                    }
                    Log.d("changable", "$batteryAvrageCurrent  ----------  $averagePower")
                } catch (e: Exception) {
                    Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
                } finally {
                    handler.postDelayed(this, 3000)
                }
            }
        }
        handler.post(AverageCurrentChecker)
    }

    /*****   LAST ROW VALUE UPDATATION MIN MAX, AVERAGE *****/
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
