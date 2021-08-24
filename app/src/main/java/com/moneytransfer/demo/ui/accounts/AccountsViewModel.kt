package com.moneytransfer.demo.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneytransfer.accounts.AccountsFeature
import com.moneytransfer.core.ktor.KtorMockService
import com.moneytransfer.transfer.TransferFeature
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

class AccountsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = AccountsFeature.getFromAccountsModule()
    }
    val text: LiveData<String> = _text

}