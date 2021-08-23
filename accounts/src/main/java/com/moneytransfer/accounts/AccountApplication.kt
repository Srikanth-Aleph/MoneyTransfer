package com.moneytransfer.accounts

import android.app.Application
import android.content.Context
import com.moneytransfer.accounts.di.useCaseModule
import com.moneytransfer.accounts.di.viewModelModule
import com.moneytransfer.accounts.di.repositoryModule
import com.moneytransfer.accounts.di.timberLogger
import com.moneytransfer.core.ktor.KtorMockService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AccountApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AccountApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        KtorMockService.startKtorServer(applicationContext())

        startKoin {
            timberLogger()
            androidContext(this@AccountApplication)
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