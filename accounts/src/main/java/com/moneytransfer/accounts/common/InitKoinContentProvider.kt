package com.moneytransfer.accounts.common

import android.content.ContentProvider
import com.moneytransfer.accounts.di.repositoryModule
import com.moneytransfer.accounts.di.useCaseModule
import com.moneytransfer.accounts.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.koinApplication

abstract class InitKoinContentProvider: ContentProvider() {
    override fun onCreate(): Boolean {
        AccountsKoinContext.koinApplication = koinApplication {
            androidContext(context!!.applicationContext)
            loadKoinModules(
                listOf(
//                    repositoryModule,
//                    useCaseModule,
//                    viewModelModule
                )
            )
        }
        return true
    }
}