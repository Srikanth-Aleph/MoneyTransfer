package com.moneytransfer.demo.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneytransfer.core.ktor.KtorMockService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

class AccountsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //Need to move this implementation to API block
        KtorMockService.startKtorServer("accounts")
        value = makeRequest("accounts")
    }

    private fun makeRequest(endPoint: String): String = runBlocking() {
        HttpClient(CIO).use { client ->
            return@runBlocking client.get<String>(port = 8080, path = endPoint)
        }
    }
    val text: LiveData<String> = _text

}