package com.mobivone.chargemeter.utils

data class batteryData(
    val health: String,
    val batteryTechnology: String?,
    val percentage: Int,
    val plugged: String,
    val status: String,
    val batteryVoltage: Float,
    val chargingSpeed: String,
    val batteryTemprature: String,
    val batteryCapacity: Int,
    val batteryEnergy: Int,
    val averageCurrent:Int,
    val averagePower: String,

    )
