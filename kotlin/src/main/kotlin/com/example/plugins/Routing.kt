package com.example.plugins

import com.example.plugins.configureRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

//-------------------------------------------
import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

//@
// ("/customer")
@Serializable
data class Customer(val name: String? = null, val age: Int? = null)

@Serializable
data class MathRequest(
    val a: Int,
    val b: Int
)

@Serializable
data class MathResult(
    val value: Int
)


fun Application.configureRouting() {
    //install(Resources)
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

        //----------------------------------------------------------
        // documented "get"-route
        get("hello", {
            description = "Hello World Endpoint."
            response {
                HttpStatusCode.OK to {
                    description = "Successful Request"
                    body<String> { description = "the response" }
                }
                HttpStatusCode.InternalServerError to {
                    description = "Something unexpected happened"
                }
            }
        }) {
            call.respondText("Hello World!")
        }
        //----------------------------------------------------------
        //curl -X "POST" "http://localhost:8080/math/add" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"a\": 10, \"b\": 9}"
        // {"value":19}
        post("math/{operation}", {
           // tags = listOf("test")
            description = "Performs the given operation on the given values and returns the result"
            request {
                pathParameter<String>("operation") {
                    description = "the math operation to perform. Either 'add' or 'sub'"
                }
                body<MathRequest>()
            }
            response {
                HttpStatusCode.OK to {
                    description = "The operation was successful"
                    body<MathResult> {
                        description = "The result of the operation"
                    }
                }
                HttpStatusCode.BadRequest to {
                    description = "An invalid operation was provided"
                }
            }
        }) {
            val operation = call.parameters["operation"]!!
            call.receive<MathRequest>().let { request ->
                when (operation) {
                    "add" -> call.respond(HttpStatusCode.OK, MathResult(request.a + request.b))
                    "sub" -> call.respond(HttpStatusCode.OK, MathResult(request.a - request.b))
                    else -> call.respond(HttpStatusCode.BadRequest, Unit)
                }
            }
        }



        ///_--------------------------------------------------------
        get("/qstr", {
            request {
                queryParameter<String>("my_p1") {
                    description = "query param num 1"; required = true; allowEmptyValue = false; explode = false; allowReserved = false
                }
                queryParameter<Int>("my_p2") {
                    description = "query param num 2"; required = false; allowEmptyValue = false; explode = false; allowReserved = false
                }
            }
            response {
                HttpStatusCode.OK to {
                    description = "The query successful"
                    body<String> {
                        description = "echoes the query params"
                    }
                }
                HttpStatusCode.BadRequest to {
                    description = "An invalid query was provided"
                }
            }

        }) {
            //val ct = call.request.contentType()
            val p1 : String?  =  call.parameters["my_p1"]
            val p2 : Int? =  call.parameters["my_p2"]?.toIntOrNull()
            call.respondText("Hello my_p1= $p1,  my_p2= $p2")
        }
        //post<Customer>(){
        post("/customers", {
            description = "Submit new customer details"
            request {
                body<List<Customer>>()
            }
            response {
                HttpStatusCode.OK to {
                    description = "New multi customers submission was successful"
                    body<String> {
                        description = "Summary of customers created"
                    }
                }
                HttpStatusCode.BadRequest to {
                    description = "Customers could not be created"
                }
            }

        } ) {

           var custs = call.receive<List<Customer>>()
            call.respondText("Customers received $custs",  status = HttpStatusCode.OK)
        }

        post("/customer", {
            description = "Submit new customer details"
            request {
                body<Customer>()
            }
            response {
                HttpStatusCode.OK to {
                    description = "New customer  submission was successful"
                    body<String> {
                        description = "Summary of customer created"
                    }
                }
                HttpStatusCode.BadRequest to {
                    description = "Customer could not be created"
                }
            }

        } ) {
//            println(call.request.headers.getAll("Content-Type")
            //val payload = call.receiveText().takeIf { it.isNotBlank() } ?: "{}"

            //println(".....Received payload: $payload")
            //val cust1 = Json.decodeFromString<Customer>(payload)
            //println(".......cust1: $cust1")
            //call.respondText("Customer  $cust1.name, $cust1.age received", status = HttpStatusCode.Created)

            var cust: Customer? = null
            try {
                // val payload = call.receiveText().takeIf { it.isNotBlank() } ?: "{}"
                // cust  = Json.decodeFromString<Customer>(payload)
                 cust = call.receive<Customer>()
                //cust = Json.decodeFromString<Customer>(payload)
//            } catch (e: SerializationException) {
//                println("....BadRequest - Malformed JSON payload")
//                call.respond(HttpStatusCode.BadRequest, "Malformed JSON payload")
            } catch (ex: Exception) {
                println("....Other exception - cause: ${ex.cause}, message: ${ex.message}")
                call.respond(HttpStatusCode.BadRequest, "General exception")
            }
            println("....OK - trying to respond with customer")
                // val cust = call.receive<Customer>()
            if (cust == null){
                println("....cust is null")
                call.respondText("Your response content",  status = HttpStatusCode.BadRequest)
                //call.respond(HttpStatusCode.BadRequest, ContentType.Application.Json, "Coudnt create customer from parameters")
            }else{
                println("....cust is not null")
                call.respondText("...post ok Customer  :-) $cust?.name, $cust?.age received", status = HttpStatusCode.Created)
            }
        }
    }
}
