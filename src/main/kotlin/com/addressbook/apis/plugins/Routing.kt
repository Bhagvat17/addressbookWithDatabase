package com.example.plugins

import com.addressbook.apis.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        personRouting()
        emailRouting()
        phoneRouting()
        addressRouting()
        groupRouting()
        groupPersonRouting()
    }
}