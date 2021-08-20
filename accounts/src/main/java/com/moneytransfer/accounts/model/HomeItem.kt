package com.moneytransfer.accounts.model

import com.google.gson.annotations.SerializedName

data class HomeItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("accountNumber")
    val accountNumber: String,
    @SerializedName("accountName")
    val accountName: String,
    @SerializedName("availableBalance")
    val availableBalance: String,
)
