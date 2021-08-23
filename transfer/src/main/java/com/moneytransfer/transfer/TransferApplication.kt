package com.moneytransfer.transfer

import android.app.Application
import android.content.Context
import com.moneytransfer.core.ktor.KtorMockService
import com.moneytransfer.transfer.di.repositoryModule
import com.moneytransfer.transfer.di.timberLogger
import com.moneytransfer.transfer.di.useCaseModule
import com.moneytransfer.transfer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TransferApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KtorMockService.startKtorServer(applicationContext())

        startKoin{
            timberLogger()
            androidContext(this@TransferApplication)
            modules(
                listOf(
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    init {
        instance = this
    }

    companion object {
        private var instance: TransferApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}