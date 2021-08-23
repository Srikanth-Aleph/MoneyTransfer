package com.moneytransfer.transfer.di

import com.moneytransfer.transfer.ui.success.SuccessViewModel
import com.moneytransfer.transfer.ui.transfer.TransferViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TransferViewModel(get()) }
    viewModel { SuccessViewModel() }
}
