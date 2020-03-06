package com.udemy.kafkaconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaconsumerApplication

fun main(args: Array<String>) {
	runApplication<KafkaconsumerApplication>(*args)
}
