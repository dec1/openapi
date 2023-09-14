package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

@Serializable
data class Customer(val name: String? = null, val age: Int? = null)

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
        get("/qstr") {
            //val ct = call.request.contentType()
            val p1 : Int?  =  call.parameters["my_p1"]?.toIntOrNull()
            val p2 : Int? =  call.parameters["my_p2"]?.toIntOrNull()
            call.respondText("Hello test $p1")
        }

        post("/customer") {
//            println(call.request.headers.getAll("Content-Type")
            val payload = call.receiveText().takeIf { it.isNotBlank() } ?: "{}"

            println(".....Received payload: $payload")
            //val cust1 = Json.decodeFromString<Customer>(payload)
            //println(".......cust1: $cust1")
            //call.respondText("Customer  $cust1.name, $cust1.age received", status = HttpStatusCode.Created)

            var cust: Customer? = null
            try {
                //cust = call.receive<Customer>()
                cust = Json.decodeFromString<Customer>(payload)
//            } catch (e: SerializationException) {
//                println("....BadRequest - Malformed JSON payload")
//                call.respond(HttpStatusCode.BadRequest, "Malformed JSON payload")
            } catch (ex: Exception) {
                println("....Other exception - cause: ${ex.cause}, message: ${ex.message}")
                call.respond(HttpStatusCode.BadRequest, "General exception")
            }
            println("....OK - trying to repond with customer")
                // val cust = call.receive<Customer>()
            call.respondText("Customer  $cust?.name, $cust?.age received", status = HttpStatusCode.Created)
        }
    }
}
