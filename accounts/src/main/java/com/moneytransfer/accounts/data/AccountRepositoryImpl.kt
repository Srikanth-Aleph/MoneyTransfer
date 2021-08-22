package com.moneytransfer.accounts.data

import com.google.gson.Gson
import com.moneytransfer.accounts.AccountApplication
import com.moneytransfer.accounts.model.response.AccountsResponse
import com.moneytransfer.accounts.model.response.AccountItem
import com.moneytransfer.accounts.repository.AccountRepository
import com.moneytransfer.core.Response
import com.moneytransfer.core.ktor.KtorMockService
import com.moneytransfer.core.ktor.model.request.AccountsRequest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@Suppress("detekt.TooGenericExceptionCaught")
class AccountRepositoryImpl : AccountRepository {
    private val ktorHttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30 * 1000
        }
    }

    override suspend fun getAccounts(): Response<List<AccountItem>> {
        return try {
            KtorMockService.startKtorServer(AccountApplication.applicationContext())
            val response = makeRequest("/getAccounts", AccountsRequest("b3a46884-84ac-4b29-985f-b3c8eebf7e19"))
            val accountsResponse = Gson().fromJson(response, AccountsResponse::class.java)
            Response.Success(accountsResponse.payLoad.accounts)
        } catch (e: Exception) {
            Timber.e(e)
            Response.Error(e)
        }
    }

    private fun makeRequest(endPoint: String, requestBody: AccountsRequest): String = runBlocking() {
        ktorHttpClient.use { client ->
            return@runBlocking client.post<String>(
                port = 8080,
                path = endPoint,
                body = requestBody,
            ) {
                contentType(ContentType.Application.Json)
            }
        }
    }
}
