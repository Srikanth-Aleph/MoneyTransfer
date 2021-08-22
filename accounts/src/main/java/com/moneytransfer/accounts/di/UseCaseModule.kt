package com.moneytransfer.accounts.di

import com.moneytransfer.accounts.domain.GetAccountUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAccountUseCase(get()) }
}
