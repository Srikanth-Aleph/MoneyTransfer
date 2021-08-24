package com.moneytransfer.accounts.common

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface AccountsKoinComponent: KoinComponent {
    override fun getKoin(): Koin {
        return AccountsKoinContext.koinApplication.koin
    }
}