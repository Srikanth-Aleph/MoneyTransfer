package com.moneytransfer.accounts.data

import android.util.Log
import com.google.gson.Gson
import com.moneytransfer.accounts.model.response.AccountItem
import com.moneytransfer.accounts.model.response.AccountsResponse
import com.moneytransfer.accounts.repository.AccountRepository
import com.moneytransfer.core.Response
import com.moneytransfer.core.model.request.AccountRequest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.engine.*
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException

@Suppress("detekt.TooGenericExceptionCaught")
class AccountRepositoryImpl : AccountRepository {
    private val ktorHttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
//        install(HttpTimeout) {
//            requestTimeoutMillis = 30 * 1000
//        }
    }

    override suspend fun getAccounts(): Response<List<AccountItem>> {
        return try {
            val response =
                makeRequest("/getAccounts", AccountRequest("b3a46884-84ac-4b29-985f-b3c8eebf7e19"))
            val accountsResponse = Gson().fromJson(response, AccountsResponse::class.java)
            Response.Success(accountsResponse.payLoad.accounts)
        } catch (e: Exception) {
            Timber.e(e)
            Response.Error(e)
        }
    }

    private suspend fun makeRequest(endPoint: String, requestBody: AccountRequest): String =
        coroutineScope {
            return@coroutineScope ktorHttpClient.use { client ->
                client.post<String>(
                    port = 8080,
                    path = endPoint,
                    body = requestBody,
                ) {
                    contentType(ContentType.Application.Json)
                }
            }
        }
}

fun CoroutineScope.safeLaunch(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch {
        try {
            block()
        } catch (ce: CancellationException) {
            // You can ignore or log this exception
        } catch (e: Exception) {
            // Here it's better to at least log the exception
            Timber.e(e, "Coroutine error")
        }
    }
}
