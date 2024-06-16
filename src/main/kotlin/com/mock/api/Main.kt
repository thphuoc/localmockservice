package com.mock.api

import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.io.FileReader


fun main() {
    val reader = File("./mock.json")
    if (reader.exists().not()) {
        println("Missing mock.json")
        return
    }
    val mock = Gson().fromJson(FileReader("./mock.json").readText(), Mock::class.java)
    embeddedServer(
        Netty,
        host = mock.host,
        port = mock.port,
        module = {
            install(ContentNegotiation) {
                gson()
            }
            install(Compression) {
                gzip()
                deflate()
            }
            routing {
                mock.endpoints.forEach { endpoint ->
                    route(endpoint.name) {
                        when (endpoint.method) {
                            "POST" -> {
                                post {
                                    call.respond(
                                        HttpStatusCode(endpoint.httpCode, ""),
                                        endpoint.response,
                                    )
                                }
                            }

                            "GET" -> {
                                get {
                                    call.respond(
                                        HttpStatusCode(endpoint.httpCode, ""),
                                        endpoint.response
                                    )
                                }
                            }

                            "PUT" -> {
                                put {
                                    call.respond(HttpStatusCode(endpoint.httpCode, ""), endpoint.response)
                                }
                            }

                            "DELETE" -> {
                                delete {
                                    call.respond(HttpStatusCode(endpoint.httpCode, ""), endpoint.response)
                                }
                            }
                        }
                    }
                }
            }
        }
    ).start(wait = true)
}