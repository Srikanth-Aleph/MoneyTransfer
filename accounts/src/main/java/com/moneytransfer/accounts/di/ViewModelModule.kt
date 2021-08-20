package com.moneytransfer.accounts.di

import com.moneytransfer.accounts.ui.AccountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AccountsViewModel(get()) }
}
