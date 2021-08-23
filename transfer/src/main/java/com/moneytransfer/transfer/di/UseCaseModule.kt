package com.moneytransfer.transfer.di


import com.moneytransfer.transfer.domain.SubmitTransferUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SubmitTransferUseCase(get()) }
}
