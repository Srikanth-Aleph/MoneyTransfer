package com.moneytransfer.core.ktor

import com.moneytransfer.core.ktor.model.accountsStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.listAccountsRoute(endPoint: String) {
    get("/$endPoint") {
        if (accountsStorage.isNotEmpty()) {
            call.respond(accountsStorage)
        } else {
            call.respondText("No accounts found", status = HttpStatusCode.NotFound)
        }
    }
}

