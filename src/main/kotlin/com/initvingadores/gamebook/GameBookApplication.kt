package com.initvingadores.gamebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GameBookApplication

fun main(args: Array<String>) {
    runApplication<GameBookApplication>(*args)
}
