package com.moneytransfer.demo

import android.app.Application
import org.koin.core.context.startKoin
import timber.log.Timber


//import org.koin.core.context.startKoin




class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {

        }



//        startKoin {
//            timberLogger()
//            androidContext(this@MainApplication)
//            modules(
//                listOf(
//                    repositoryModule,
//                    useCaseModule,
//                    viewModelModule
//                )
//            )
//        }







    }
}
