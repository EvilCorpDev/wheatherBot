package com.co2.messure.co2sensor.rest

import com.co2.messure.co2sensor.rest.model.CO2Data
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "co2SensorClient",
             url = "\${co2Sensor-client.ppm.host}",
             path = "\${co2Sensor-client.ppm.context-path}")
interface CO2SensorClient {

    @GetMapping("/")
    fun getCo2Ppm(): CO2Data
}