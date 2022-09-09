package com.kotlin.play

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlayApplication

fun main(args: Array<String>) {
	runApplication<PlayApplication>(*args)
}
