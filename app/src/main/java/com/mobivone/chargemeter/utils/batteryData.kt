package com.mobivone.chargemeter.utils

data class batteryData(
    val percentage: Int,
    val health: String,
    val batteryTechnology: String?,
    val plugged: String,
    val status: String,
    val batteryVoltage: Float,
    val batteryTemprature: String,
    val batteryAvrageCurrent: Int,
    val batteryCapacity: Int,
    val batteryEnergy: Int,
    val chargingSpeed: String,
    val formatedAverageWatt: String,
    val avragePower: Float,
    val batteryMeasureAverageCurrent: Float,
    val spotCurrent: String,
    val averageWatt: Double
)
