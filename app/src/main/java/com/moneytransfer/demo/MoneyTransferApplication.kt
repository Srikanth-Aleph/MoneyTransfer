package com.moneytransfer.demo

import android.app.Application
import android.content.Context
import com.moneytransfer.accounts.di.repositoryModule
import com.moneytransfer.accounts.di.timberLogger
import com.moneytransfer.accounts.di.useCaseModule
import com.moneytransfer.accounts.di.viewModelModule
import com.moneytransfer.core.ktor.KtorMockService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoneyTransferApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MoneyTransferApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        KtorMockService.startKtorServer(applicationContext())
        startKoin {
            timberLogger()
            androidContext(this@MoneyTransferApplication)
            modules(
                listOf(
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}