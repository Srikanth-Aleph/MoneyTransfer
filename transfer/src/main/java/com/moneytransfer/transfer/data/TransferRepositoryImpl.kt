package com.moneytransfer.transfer.data

import com.google.gson.Gson
import com.moneytransfer.core.Response
import com.moneytransfer.core.ktor.KtorMockService
import com.moneytransfer.transfer.TransferApplication
import com.moneytransfer.transfer.model.TransferResponse
import com.moneytransfer.transfer.repository.TransferRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@Suppress("detekt.TooGenericExceptionCaught")
class TransferRepositoryImpl : TransferRepository {

    override suspend fun submitTransferRequest(): Response<TransferResponse> {

        return try {

            KtorMockService.startKtorServer(TransferApplication.applicationContext())
            val value = makeRequest("getAccounts")
            val gson = Gson()
            val testModel = gson.fromJson(value, TransferResponse::class.java)
            Response.Success(testModel)

        } catch (e: Exception) {
            Timber.e(e)
            Response.Error(e)
        }
    }

    private fun makeRequest(endPoint: String): String = runBlocking() {
        HttpClient(CIO).use { client ->
            return@runBlocking client.get<String>(port = 8080, path = endPoint)
        }
    }
}
