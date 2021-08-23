package com.moneytransfer.transfer.model

data class Payload(
    val anotherIrrelevantMetaData: String,
    val irrelevantMetaData: Int,
    val responseId: String,
    val transactionStatus: String
)