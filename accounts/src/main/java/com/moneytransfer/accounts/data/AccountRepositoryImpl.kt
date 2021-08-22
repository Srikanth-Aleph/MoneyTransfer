package com.moneytransfer.accounts.data

import com.google.gson.Gson
import com.moneytransfer.accounts.AccountApplication
import com.moneytransfer.accounts.model.AccountResponse
import com.moneytransfer.accounts.model.HomeItem
import com.moneytransfer.accounts.repository.AccountRepository
import com.moneytransfer.core.Response
import com.moneytransfer.core.ktor.KtorMockService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@Suppress("detekt.TooGenericExceptionCaught")
class AccountRepositoryImpl : AccountRepository {
    override suspend fun getAccounts(): Response<List<HomeItem>> {

        return try {

            KtorMockService.startKtorServer(AccountApplication.applicationContext())
            val value = makeRequest("getAccounts")
            val gson = Gson()
            val testModel = gson.fromJson(value, AccountResponse::class.java)
            Response.Success(testModel.payLoad.accounts)
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
