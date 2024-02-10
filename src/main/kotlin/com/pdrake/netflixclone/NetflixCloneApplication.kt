package com.pdrake.netflixclone

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NetflixCloneApplication

fun main(args: Array<String>) {
    runApplication<NetflixCloneApplication>(*args)
}
