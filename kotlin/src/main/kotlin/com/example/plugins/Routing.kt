package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

data class Customer(val name: String, val age: Int)

fun Application.configureRouting() {
    routing {
        //openAPI(path="openapi", swaggerFile = "openapi/documentation.yaml")

        get("/") {
            val n =  call.parameters["name"]
            print(n)
            if (call.request.queryParameters["price"] == "asc") {
                print("asc")
                // Show products from the lowest price to the highest
            }
            call.respondText("Hello $n")
        }
        get("/qstr/") {
            val ct = call.request.contentType()
            val p =  call.request.queryParameters["p4"]?.toInt()
            call.respondText("Hello test}")
        }

        post("/customer") {
            val cust = call.receive<Customer>()
            call.respondText("Customer  $cust.name, $cust.age received", status = HttpStatusCode.Created)
        }

    }
}
