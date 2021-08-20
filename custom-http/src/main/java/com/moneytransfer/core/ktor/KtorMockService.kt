package com.moneytransfer.core.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*


object KtorMockService {

    fun startKtorServer(endPoint: String) {
        val server = embeddedServer(CIO, 8080) {
            install(ContentNegotiation) {
                json()
            }
            routing {
                get("/accounts") {
                    call.respond(mapOf("message" to "Need to load accounts list JSON"))
                }
            }
        }
        server.start()
    }
}