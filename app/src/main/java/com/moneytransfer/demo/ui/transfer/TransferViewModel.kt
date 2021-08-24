package com.moneytransfer.demo.ui.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneytransfer.transfer.TransferFeature.getFromTransferModule

class TransferViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = getFromTransferModule()
    }
    val text: LiveData<String> = _text
}