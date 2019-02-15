package com.co2.messure.co2sensor.bots

import com.co2.messure.co2sensor.rest.model.CO2Data

interface CO2 {
    fun getCurrentCO2Ppm(): CO2Data
}