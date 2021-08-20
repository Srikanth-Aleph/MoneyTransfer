package com.moneytransfer.accounts.data

import com.google.gson.Gson
import com.moneytransfer.accounts.AccountApplication
import com.moneytransfer.accounts.model.AccountResponse
import com.moneytransfer.accounts.model.HomeItem
import com.moneytransfer.accounts.repository.AccountRepository
import com.moneytransfer.core.Response
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

@Suppress("detekt.TooGenericExceptionCaught")
class AccountRepositoryImpl : AccountRepository {
    override suspend fun getAccounts(): Response<List<HomeItem>> {

        return try {

            var gson = Gson()
            var testModel = gson.fromJson(loadJSONFromAsset(), AccountResponse::class.java)
            Timber.d("$testModel.payLoad.irrelevantData")
            Response.Success(testModel.payLoad.accounts)
        } catch (e: Exception) {
            Timber.e(e)
            Response.Error(e)
        }
//        private val _text = MutableLiveData<String>().apply {
//            //Need to move this implementation to API block
//            KtorMockService.startKtorServer("accounts")
//            value = makeRequest("accounts")
//        }
//
//        private fun makeRequest(endPoint: String): String = runBlocking() {
//            HttpClient(CIO).use { client ->
//                return@runBlocking client.get<String>(port = 8080, path = endPoint)
//            }
//        }
//        val text: LiveData<String> = _text
    }


    fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val ls: InputStream =
                AccountApplication.applicationContext().assets.open("payload.json")
            val size: Int = ls.available()
            val buffer = ByteArray(size)
            ls.read(buffer)
            ls.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
