package com.moneytransfer.accounts.repository

import com.moneytransfer.accounts.model.AccountItem
import com.moneytransfer.core.Response

interface AccountRepository {
    suspend fun getAccounts(): Response<List<AccountItem>>
}
