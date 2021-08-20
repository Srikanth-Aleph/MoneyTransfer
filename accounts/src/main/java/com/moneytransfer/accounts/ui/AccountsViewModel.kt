package com.moneytransfer.accounts.ui

import androidx.lifecycle.*
import com.moneytransfer.accounts.domain.GetAccountUseCase
import com.moneytransfer.accounts.model.HomeItem
import com.moneytransfer.core.Result
import java.util.*

class AccountsViewModel(private val getJournalUseCase: GetAccountUseCase) : ViewModel() {


    // Inputs
    internal val onRefreshJournals = MutableLiveData<Unit>()

    // Outputs
    internal val onJournalsLoaded: LiveData<List<HomeItem>> get() = _onJournalsLoaded
    internal val onJournalsLoading: LiveData<Boolean> get() = _onJournalsLoading
    internal val onJournalsLoadingError: LiveData<Unit> get() = _onJournalsLoadingError

    // Transformations
    private val getJournalsResult: LiveData<Result<List<HomeItem>>> =
        Transformations.switchMap(onRefreshJournals) {
            getJournalUseCase.execute()
        }

    private val _onJournalsLoaded = MediatorLiveData<List<HomeItem>>()
    private val _onJournalsLoading = MediatorLiveData<Boolean>()
    private val _onJournalsLoadingError = MediatorLiveData<Unit>()

    init {
        _onJournalsLoaded.addSource(getJournalsResult) {
            if (it is Result.Success) {
                _onJournalsLoaded.postValue(it.data)
                _onJournalsLoading.postValue(false)
            }
        }
        _onJournalsLoading.addSource(getJournalsResult) {
            if (it is Result.Loading) {
                _onJournalsLoading.postValue(true)
            }
        }
        _onJournalsLoadingError.addSource(getJournalsResult) {
            if (it is Result.Error) {
                _onJournalsLoadingError.postValue(Unit)
                _onJournalsLoading.postValue(false)
            }
        }
        onRefreshJournals.postValue(Unit)
    }
}