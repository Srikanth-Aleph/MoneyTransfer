package com.moneytransfer.transfer.di

import com.moneytransfer.transfer.data.TransferRepositoryImpl
import com.moneytransfer.transfer.repository.TransferRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TransferRepositoryImpl() as TransferRepository }
}
