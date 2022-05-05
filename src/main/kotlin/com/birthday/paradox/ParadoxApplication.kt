package com.birthday.paradox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParadoxApplication

fun main(args: Array<String>) {
	runApplication<ParadoxApplication>(*args)
}
