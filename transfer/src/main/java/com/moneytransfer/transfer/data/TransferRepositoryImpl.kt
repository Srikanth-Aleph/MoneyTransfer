package com.moneytransfer.transfer.data

import com.google.gson.Gson
import com.moneytransfer.core.Response
import com.moneytransfer.core.ktor.KtorMockService
import com.moneytransfer.core.model.request.AccountRequest
import com.moneytransfer.transfer.TransferApplication
import com.moneytransfer.transfer.model.TransferRequest
import com.moneytransfer.transfer.model.TransferResponse
import com.moneytransfer.transfer.repository.TransferRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@Suppress("detekt.TooGenericExceptionCaught")
class TransferRepositoryImpl : TransferRepository {

    private val ktorHttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
    }

    override suspend fun submitTransferRequest(requestBody: TransferRequest): Response<TransferResponse> {

        return try {
            val response =
                makeRequest("/submit", AccountRequest("b3a46884-84ac-4b29-985f-b3c8eebf7e19"))
            val transferResponse = Gson().fromJson(response, TransferResponse::class.java)
            Response.Success(transferResponse)
        } catch (e: Exception) {
            Timber.e(e)
            Response.Error(e)
        }
    }

    private fun makeRequest(endPoint: String, requestBody: AccountRequest): String =
        runBlocking {
            return@runBlocking ktorHttpClient.use { client ->
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
