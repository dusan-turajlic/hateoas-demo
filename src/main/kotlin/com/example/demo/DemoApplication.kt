package com.example.demo

import com.example.demo.data.ShoppingService
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope

@SpringBootApplication
class DemoApplication {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun shoppingServiceSingleton(): ShoppingService {
        return ShoppingService()
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
