package com.co2.messure.co2sensor.config

import com.co2.messure.co2sensor.bots.impl.CO2Bot
import com.co2.messure.co2sensor.job.VentilateRoomTask
import com.co2.messure.co2sensor.rest.CO2SensorClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi

@EnableScheduling
@Configuration
@EnableFeignClients(basePackageClasses = [CO2SensorClient::class])
class BotConfig {
     init {
         ApiContextInitializer.init()
     }

    @Bean
    fun co2Bot(@Value("\${co2-bot.token}") token: String,
               @Value("\${co2-bot.chat-id}") chatId: String,
               co2Client: CO2SensorClient) = CO2Bot(token, chatId, co2Client)

    @Bean
    fun telegramBotsApi(co2Bot: CO2Bot): TelegramBotsApi {
        val telegramBotsApi = TelegramBotsApi()
        telegramBotsApi.registerBot(co2Bot)
        return telegramBotsApi
    }

    @Bean
    fun ventilateRoomTask(@Value("\${ventilate.panic.sticker.id}") panicSticker: String,
                          @Value("\${ventilate.panic.ppm}") panicPpm: Int,
                          co2Bot: CO2Bot) = VentilateRoomTask(co2Bot, panicSticker, panicPpm)
}