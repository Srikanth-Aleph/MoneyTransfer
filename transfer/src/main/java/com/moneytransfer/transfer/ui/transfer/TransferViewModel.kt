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
    internal val onTransferProcessingSuccess: LiveData<TransferResponse> get() = _onTransferProcessingSuccess
    internal val onTransferProcessing: LiveData<Boolean> get() = _onTransferProcessing
    internal val onTransferProcessingError: LiveData<Unit> get() = _onTransferProcessingError

    // Transformations
    private val onSaveTransferResult: LiveData<Result<TransferResponse>> =
        Transformations.switchMap(onTransferClicked) {

            transferInformation.value?.let {
                submitTransferUseCase.execute(it)
            }
        }

    private val _transferInformation = MediatorLiveData<TransferRequest>()
    private val _onTransferProcessingSuccess = MediatorLiveData<TransferResponse>()
    private val _onTransferProcessing = MediatorLiveData<Boolean>()
    private val _onTransferProcessingError = MediatorLiveData<Unit>()

    init {
        _onTransferProcessingSuccess.addSource(onSaveTransferResult) {
            if (it is Result.Success) {
                _onTransferProcessingSuccess.postValue(it.data!!)
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

        _transferInformation.postValue(
            TransferRequest(
                155,
                "b3a46884-84ac-4b29-985f-b3c8eebf7e19",
                "b3a46884-84ac-4b29-985f-b3c8eebf7e19",
                "b3a46884-84ac-4b29-985f-b3c8eebf7e19"
            )
        )

    }

    fun submitTransferRequest(transferRequest : TransferRequest){
        _transferInformation.postValue(transferRequest)
    }
}