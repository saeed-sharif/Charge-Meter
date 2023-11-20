package com.mobivone.chargemeter.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.BatteryManager

class batteryUtils {

    companion object {
        fun getBatteryStatusText(batterStatus: Int): String {
            return when (batterStatus) {
                BatteryManager.BATTERY_STATUS_FULL -> "Full"
                BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> "Dis Charging"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not Charging"
                BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
                else -> "Unknown"


            }
        }

        fun getBatteryPluggedText(batteryPluged: Int): String {
            return when (batteryPluged) {
                BatteryManager.BATTERY_PLUGGED_USB -> "USB"
                BatteryManager.BATTERY_PLUGGED_AC -> "AC"
                BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Dis Charging"
                else -> "Unknown"


            }




        }

        fun getBatteryHealthText(batteryHealth: Int): String {
            return when (batteryHealth) {
                BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
                BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified Failure"
                BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
                BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
                BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
                BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
                BatteryManager.BATTERY_HEALTH_UNKNOWN -> "Unknown"
                else -> "Unknown"
            }
        }

        fun getChargingSpeed(watt: Double): String {
            return when {
                watt < 5.0 -> "Slow"
                watt < 8.0 -> "Normal"
                watt < 12.0 -> "Fast"
                watt < 16 -> "Super Fast"
                else -> "Ultra Fast"
            }
        }

        @SuppressLint("PrivateApi")
        fun getBatteryCapacity(context: Context?): Double {
            val mPowerProfile: Any
            var batteryCapacity = 0.0
            val POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile"
            try {
                mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context::class.java)
                    .newInstance(context)
                batteryCapacity = Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod("getBatteryCapacity")
                    .invoke(mPowerProfile) as Double
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return batteryCapacity
        }

        fun getAvgCurrent(manager: BatteryManager): Double {
            var avgCurrent =
                (manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE) / 1000000).toDouble()
            avgCurrent = Math.abs(avgCurrent)
            return avgCurrent
        }

    }
}
