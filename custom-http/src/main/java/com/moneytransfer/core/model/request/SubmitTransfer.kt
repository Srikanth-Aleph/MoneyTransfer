package com.moneytransfer.core.model.request

data class SubmitTransfer(
    val metaData: String,
    val fromAccount: String,
    val toAccount: String,
    val amount: Int
)
