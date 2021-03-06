package com.moneytransfer.accounts.data

import com.google.gson.Gson
import com.moneytransfer.accounts.model.response.AccountItem
import com.moneytransfer.accounts.model.response.AccountsResponse
import com.moneytransfer.accounts.repository.AccountRepository
import com.moneytransfer.core.Response
import com.moneytransfer.core.model.request.AccountRequest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import kotlin.io.use

@Suppress("detekt.TooGenericExceptionCaught")
class AccountRepositoryImpl : AccountRepository {

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

    private fun makeRequest(endPoint: String, requestBody: AccountRequest): String = runBlocking {
            return@runBlocking HttpClient(CIO) {
                install(JsonFeature) {
                    serializer = GsonSerializer() {
                        setPrettyPrinting()
                        disableHtmlEscaping()
                    }
                }
            }.use { client ->
                client.post<String>(port = 8080, path = endPoint, body = requestBody) {
                    contentType(ContentType.Application.Json)
                }
            }
        }
}
