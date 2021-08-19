package com.moneytransfer.demo.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneytransfer.core.ktor.KtorMockService

class AccountsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = KtorMockService.getDummyResponse().toString()
    }
    val text: LiveData<String> = _text
}