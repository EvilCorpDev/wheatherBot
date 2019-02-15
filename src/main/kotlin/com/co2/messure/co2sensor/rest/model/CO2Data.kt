package com.co2.messure.co2sensor.rest.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CO2Data(@JsonProperty("ppm") val ppm: Int)