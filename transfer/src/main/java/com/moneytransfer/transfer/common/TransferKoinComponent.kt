package com.moneytransfer.transfer.common

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface TransferKoinComponent: KoinComponent {
    override fun getKoin(): Koin {
        return TransferKoinContext.koinApplication.koin
    }
}