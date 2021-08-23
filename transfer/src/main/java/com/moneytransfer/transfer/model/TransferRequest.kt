package com.moneytransfer.transfer.model

data class TransferRequest(
    val amount: Int,
    val fromAccount: String,
    val metaData: String,
    val toAccount: String
)