package com.moneytransfer.transfer.ui.transfer

import androidx.lifecycle.*
import com.moneytransfer.core.Result
import com.moneytransfer.transfer.domain.SubmitTransferUseCase
import com.moneytransfer.transfer.model.TransferRequest
import com.moneytransfer.transfer.model.TransferResponse

class TransferViewModel(private val submitTransferUseCase: SubmitTransferUseCase) : ViewModel() {


    // Inputs
    internal val onTransferClicked = MutableLiveData<Unit>()
    internal val onTransferRequestSubmitted = MutableLiveData<TransferRequest>()

    // Outputs
    internal val transferInformation : LiveData<TransferRequest> get() = _transferInformation
    internal val onTransferProcessingSuccess: LiveData<Result<TransferResponse>> get() = _onTransferProcessingSuccess
    internal val onTransferProcessing: LiveData<Boolean> get() = _onTransferProcessing
    internal val onTransferProcessingError: LiveData<Unit> get() = _onTransferProcessingError

    // Transformations
    private val onSaveTransferResult: LiveData<Result<TransferResponse>> =
        Transformations.switchMap(onTransferClicked) {
            submitTransferUseCase.execute()
        }

    private val _transferInformation = MediatorLiveData<TransferRequest>()
    private val _onTransferProcessingSuccess = MediatorLiveData<Result<TransferResponse>>()
    private val _onTransferProcessing = MediatorLiveData<Boolean>()
    private val _onTransferProcessingError = MediatorLiveData<Unit>()

    init {
        _onTransferProcessingSuccess.addSource(onSaveTransferResult) {
            if (it is Result.Success) {
                _onTransferProcessingSuccess.postValue(it)
                _onTransferProcessing.postValue(false)
            }
        }
        _onTransferProcessing.addSource(onSaveTransferResult) {
            if (it is Result.Loading) {
                _onTransferProcessing.postValue(true)
            }
        }
        _onTransferProcessingError.addSource(onSaveTransferResult) {
            if (it is Result.Error) {
                _onTransferProcessingError.postValue(Unit)
                _onTransferProcessing.postValue(false)
            }
        }


        _transferInformation.addSource(onTransferRequestSubmitted) {
            _transferInformation.postValue(it)
        }

    }
}