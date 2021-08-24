package com.moneytransfer.transfer.common

import android.content.ContentProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.koinApplication

abstract class InitKoinContentProvider: ContentProvider() {
    override fun onCreate(): Boolean {
        TransferKoinContext.koinApplication = koinApplication {
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