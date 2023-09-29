package com.example

import com.example.plugins.configureRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*


import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }


    install(SwaggerUI) {
        swagger {
            swaggerUrl = "swagger-ui"
            forwardRoot = true
        }
        info {
            title = "Example API"
            version = "latest"
            description = "Example API for testing and demonstration purposes."
        }
        server {
            url = "http://localhost:8080"
            description = "Development Server"
        }
    }

    configureRouting()

}
