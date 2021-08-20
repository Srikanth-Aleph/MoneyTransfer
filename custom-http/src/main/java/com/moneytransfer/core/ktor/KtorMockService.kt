package com.moneytransfer.core.ktor

import android.content.Context
import com.moneytransfer.core.utils.readAssetsFile
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*


object KtorMockService {

    fun startKtorServer(context: Context) {
        val server = embeddedServer(CIO, 8080) {
            install(ContentNegotiation) {
                json()
            }
            routing {
                get("/accounts") {
                    call.respond(context.assets.readAssetsFile("accountsList.json"))
                }
            }
        }
        server.start()
    }
}