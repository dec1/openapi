package com.example

import com.example.plugins.configureRouting
import io.ktor.server.application.*
//----------------------------------------------
// Content Negotiation
import io.ktor.server.plugins.contentnegotiation.*
// Kotlinx
// import io.ktor.serialization.kotlinx.json.*
// Jackson
import io.ktor.serialization.jackson.*


import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.io.File

fun main() {

    class User(val name: String)
    var user: User? = null
    //var user = User("tim")

    fun showUserNameIfPresent() {
// will not work, because cannot smart-cast a property
         if (user != null) {
            println("1: ${user.name}")
         }
// works
// val u = user
// if (u != null) {
// println(u.name)
// }
// perfect
            user?.let { println(it.name) }
        }
    showUserNameIfPresent()
//    val size = File("huge.file").useLines {
//        s.sumOf { it.length }
//    }

    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)


}

fun Application.module() {
    install(ContentNegotiation) {
       // json()
        jackson()
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
