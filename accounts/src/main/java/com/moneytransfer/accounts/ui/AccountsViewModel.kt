package com.moneytransfer.accounts.ui

import androidx.lifecycle.*
import com.moneytransfer.accounts.domain.GetAccountUseCase
import com.moneytransfer.core.Result

class AccountsViewModel(private val getAccountUseCase: GetAccountUseCase) : ViewModel() {


    // Inputs
    internal val onRefreshAccount = MutableLiveData<Unit>()

    // Outputs
    internal val onAccountLoaded: LiveData<List<HomeItem>> get() = _onAccountLoaded
    internal val onAccountLoading: LiveData<Boolean> get() = _onAccountLoading
    internal val onAccountLoadingError: LiveData<Unit> get() = _onAccountLoadingError

    // Transformations
    private val getAccountResult: LiveData<Result<List<HomeItem>>> =
        Transformations.switchMap(onRefreshAccount) {
            getAccountUseCase.execute()
        }

    private val _onAccountLoaded = MediatorLiveData<List<HomeItem>>()
    private val _onAccountLoading = MediatorLiveData<Boolean>()
    private val _onAccountLoadingError = MediatorLiveData<Unit>()

    init {
        _onAccountLoaded.addSource(getAccountResult) {
            if (it is Result.Success) {
                _onAccountLoaded.postValue(it.data)
                _onAccountLoading.postValue(false)
            }
        }
        _onAccountLoading.addSource(getAccountResult) {
            if (it is Result.Loading) {
                _onAccountLoading.postValue(true)
            }
        }
        _onAccountLoadingError.addSource(getAccountResult) {
            if (it is Result.Error) {
                _onAccountLoadingError.postValue(Unit)
                _onAccountLoading.postValue(false)
            }
        }
        onRefreshAccount.postValue(Unit)
    }
}