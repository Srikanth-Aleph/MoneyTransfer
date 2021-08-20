package com.moneytransfer.accounts.model

import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("payload")
    val payLoad: Account
)
