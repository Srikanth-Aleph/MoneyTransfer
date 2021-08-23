package com.moneytransfer.accounts.repository

import com.moneytransfer.accounts.model.response.AccountItem
import com.moneytransfer.core.Response

interface AccountRepository {
    suspend fun getAccounts(): Response<List<AccountItem>>
}
