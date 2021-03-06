package com.moneytransfer.transfer.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moneytransfer.core.Response
import com.moneytransfer.core.Result
import com.moneytransfer.core.UseCaseWithParameter
import com.moneytransfer.transfer.model.TransferRequest
import com.moneytransfer.transfer.model.TransferResponse
import com.moneytransfer.transfer.repository.TransferRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SubmitTransferUseCase(private val transferRepo: TransferRepository) :
    UseCaseWithParameter<TransferRequest,LiveData<Result<TransferResponse>>>, UseCaseScope {

    override fun execute(transferRequest: TransferRequest): LiveData<Result<TransferResponse>> {

        val result = MutableLiveData<Result<TransferResponse>>()
        result.postValue(Result.Loading)

        launch {
            val toPost = when (val response = transferRepo.submitTransferRequest(transferRequest)) {
                is Response.Success -> Result.Success(response.data)
                is Response.Error -> Result.Error(response.exception)
            }

            result.postValue(toPost)
        }

        return result
    }

    override fun cancel() {
        coroutineContext.cancel()
    }
}
