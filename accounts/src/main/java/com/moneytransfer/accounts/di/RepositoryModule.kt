package com.moneytransfer.accounts.di

import com.moneytransfer.accounts.data.AccountRepositoryImpl
import com.moneytransfer.accounts.repository.AccountRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AccountRepositoryImpl() as AccountRepository }
}
