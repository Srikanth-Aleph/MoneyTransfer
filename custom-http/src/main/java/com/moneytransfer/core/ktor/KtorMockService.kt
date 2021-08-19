package com.moneytransfer.core.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

object KtorMockService {
    fun getDummyResponse(endPoint: String) {
        embeddedServer(Netty, 8080) {
            install(ContentNegotiation) {
                json()
            }
            routing {
                listAccountsRoute(endPoint)
            }
        }
    }
}