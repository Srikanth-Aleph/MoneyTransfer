package com.moneytransfer.accounts

import com.moneytransfer.core.SampleCore

object AccountsFeature {
    fun getFromAccountsModule(): String {
        return SampleCore.getFromCoreFunction("This is", "accounts", "module")
    }
}