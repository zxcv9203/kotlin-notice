package com.example.notice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class KotlinNoticeApplication

fun main(args: Array<String>) {
    runApplication<KotlinNoticeApplication>(*args)
}
