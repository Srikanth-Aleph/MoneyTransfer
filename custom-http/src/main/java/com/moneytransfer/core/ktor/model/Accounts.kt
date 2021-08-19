package com.moneytransfer.core.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class Accounts(val number: String, val contents: List<AccountsItem>)

@Serializable
data class AccountsItem(val name: String, val bank: String)


val accountsStorage = listOf(Accounts(
    "11234567", listOf(
        AccountsItem("Name1", "Bank1")
    )),
    Accounts("12345678", listOf(
        AccountsItem("Name2", "Bank2")
    ))
)