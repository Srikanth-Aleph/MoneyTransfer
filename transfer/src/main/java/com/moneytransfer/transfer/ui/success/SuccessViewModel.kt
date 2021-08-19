package com.moneytransfer.transfer.ui.success

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneytransfer.transfer.TransferFeature.getFromTransferModule

class SuccessViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = getFromTransferModule()
    }
    val text: LiveData<String> = _text
}