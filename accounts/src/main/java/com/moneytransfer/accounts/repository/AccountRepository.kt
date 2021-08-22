package com.moneytransfer.accounts.repository

import com.moneytransfer.core.Response

interface AccountRepository {
    suspend fun getAccounts(): Response<List<HomeItem>>
}
