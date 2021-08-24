package com.moneytransfer.transfer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload(
    val anotherIrrelevantMetaData: String,
    val irrelevantMetaData: Int,
    val responseId: String,
    val transactionStatus: String
) :  Parcelable