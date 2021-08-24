package com.moneytransfer.transfer.data

import com.google.gson.Gson
import com.moneytransfer.core.Response
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

    override suspend fun submitTransferRequest(requestBody: TransferRequest): Response<TransferResponse> {
        return try {
            val response =
                makeRequest("/submit", requestBody)
            val transferResponse = Gson().fromJson(response, TransferResponse::class.java)
            Response.Success(transferResponse)
        } catch (e: Exception) {
            Timber.e(e)
            Response.Error(e)
        }
    }

    private fun makeRequest(endPoint: String, requestBody: TransferRequest): String =
        runBlocking {
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
