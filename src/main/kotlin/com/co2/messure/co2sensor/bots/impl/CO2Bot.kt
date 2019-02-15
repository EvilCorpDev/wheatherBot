package com.co2.messure.co2sensor.bots.impl

import com.co2.messure.co2sensor.bots.CO2
import com.co2.messure.co2sensor.rest.CO2SensorClient
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.Update

class CO2Bot(private val token: String, private val chatId: String, private val co2Client: CO2SensorClient)
    : TelegramLongPollingBot(), CO2 {

    override fun getBotUsername() = "co2Bot"

    override fun getBotToken() = token

    override fun onUpdateReceived(update: Update?) {
        if (update?.message?.text == "/ppm") {
            val co2Data = co2Client.getCo2Ppm()
            senMessage("Current co2 ppm: ${co2Data.ppm}")
        }
    }

    fun senMessage(msg: String) {
        val sendMsg = SendMessage(chatId, msg)
        execute(sendMsg)
    }

    fun sendSticker(sticker: String) {
        val sendSticker = SendSticker()
        sendSticker.chatId = chatId
        sendSticker.setSticker(sticker)

        execute(sendSticker)
    }

    override fun getCurrentCO2Ppm() = co2Client.getCo2Ppm()

}