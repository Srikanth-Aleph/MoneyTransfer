package com.moneytransfer.demo.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneytransfer.accounts.AccountsFeature

class AccountsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = AccountsFeature.getFromAccountsModule()
    }
    val text: LiveData<String> = _text
}