package com.moneytransfer.transfer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransferResponse(
    val payload: Payload
): Parcelable