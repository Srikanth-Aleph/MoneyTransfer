package com.moneytransfer.core.ktor

import android.content.Context
import com.moneytransfer.core.model.request.AccountRequest
import com.moneytransfer.core.model.request.SubmitTransfer
import com.moneytransfer.core.utils.readAssetsFile
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*


object KtorMockService {

    fun startKtorServer(context: Context) {
        val server = embeddedServer(CIO, 8080) {
            install(ContentNegotiation) {
                gson()
            }
            routing {
                post("/getAccounts") {
                    val accountsRequest = call.receive<AccountRequest>()
                    if (accountsRequest.metaData.isNotEmpty())
                        call.respond(context.assets.readAssetsFile("accountsList.json"))
                    else
                        call.respond(HttpStatusCode.BadRequest)
                }

                post("/submit") {
                    val submitTransfer = call.receive<SubmitTransfer>()
                    if (submitTransfer.metaData.isNotEmpty())
                        call.respond(context.assets.readAssetsFile("submitResponse.json"))
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
        server.start()
    }
}