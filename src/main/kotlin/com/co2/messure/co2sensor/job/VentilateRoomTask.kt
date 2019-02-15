package com.co2.messure.co2sensor.job

import com.co2.messure.co2sensor.bots.impl.CO2Bot
import com.co2.messure.co2sensor.rest.model.CO2Data
import org.springframework.scheduling.annotation.Scheduled
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter
import java.time.LocalDateTime

class VentilateRoomTask(private val co2Bot: CO2Bot, private val panicSticker: String, private val panicPpm: Int) {

    private val fileName = "D:\\Dev\\co2Sensor\\sensordata.txt"

    @Scheduled(fixedRate = 60000)
    fun scheduleTaskWithFixedRate() {
        val ppmData = co2Bot.getCurrentCO2Ppm()
        val out = PrintWriter(BufferedWriter(FileWriter(fileName, true)))
        out.println(constructStat(ppmData))
        out.close()

        if(ppmData.ppm > panicPpm) {
            co2Bot.senMessage("Pls ventilate your room bro, current co2 ppm: ${ppmData.ppm}")
            co2Bot.sendSticker(panicSticker)
        }
    }

    private fun constructStat(data: CO2Data) = "${LocalDateTime.now()} ppm: ${data.ppm}"
}