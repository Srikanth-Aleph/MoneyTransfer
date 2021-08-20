package com.moneytransfer.accounts.model

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("responseId")
    val id: String,
    @SerializedName("irrelevantMetaData")
    val irrelevantData:Int,
    @SerializedName("anotherIrrelevantMetaData")
    val anotherIrrelevantMetaData: String,
    @SerializedName("accounts")
    val accounts: List<HomeItem>

)
