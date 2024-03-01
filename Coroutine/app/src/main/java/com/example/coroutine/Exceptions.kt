package com.example.coroutine

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReportException())
        println("Have a good day!")
    }
}

suspend fun getWeatherReportException() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async {
        try {
            getTemperatureError()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }

    "${forecast.await()} ${temperature.await()}"
}

suspend fun getTemperatureError(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}