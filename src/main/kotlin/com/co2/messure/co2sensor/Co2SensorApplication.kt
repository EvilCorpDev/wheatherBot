package com.co2.messure.co2sensor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [
    SecurityAutoConfiguration::class,
    SecurityFilterAutoConfiguration::class,
    ThymeleafAutoConfiguration::class,
    UserDetailsServiceAutoConfiguration::class
])
class Co2SensorApplication

fun main(args: Array<String>) {
    runApplication<Co2SensorApplication>(*args)
}

