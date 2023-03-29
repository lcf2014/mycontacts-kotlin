package com.lufaria.mycontacts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class MycontactsKotlinApplication

fun main(args: Array<String>) {
	runApplication<MycontactsKotlinApplication>(*args)
}
