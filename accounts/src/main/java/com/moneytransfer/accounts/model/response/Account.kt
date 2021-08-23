package com.moneytransfer.accounts.model

import com.google.gson.annotations.SerializedName

data class AccountsResponse(
    @SerializedName("payload")
    val payLoad: AccountsPayload
)

data class AccountsPayload(
    @SerializedName("responseId")
    val id: String,
    @SerializedName("irrelevantMetaData")
    val irrelevantData: Int,
    @SerializedName("anotherIrrelevantMetaData")
    val anotherIrrelevantMetaData: String,
    @SerializedName("accounts")
    val accounts: List<AccountItem>

)

data class AccountItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("accountNumber")
    val accountNumber: String,
    @SerializedName("accountName")
    val accountName: String,
    @SerializedName("availableBalance")
    val availableBalance: String,
)
