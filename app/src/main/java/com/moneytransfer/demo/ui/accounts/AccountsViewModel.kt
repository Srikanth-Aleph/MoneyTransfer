package com.moneytransfer.demo.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneytransfer.feature.SampleFeature

class AccountsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = SampleFeature.getFromCoreModule()
    }
    val text: LiveData<String> = _text
}