package com.moneytransfer.accounts.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moneytransfer.accounts.model.HomeItem
import com.moneytransfer.accounts.repository.AccountRepository
import com.moneytransfer.core.Response
import com.moneytransfer.core.Result
import com.moneytransfer.core.UseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class GetAccountUseCase(private val accountRepo: AccountRepository) :
    UseCase<LiveData<Result<List<HomeItem>>>>, UseCaseScope {
    override fun execute(): LiveData<Result<List<HomeItem>>> {
        val result = MutableLiveData<Result<List<HomeItem>>>()
        result.postValue(Result.Loading)
        launch {
            val toPost = when (val response = accountRepo.getAccounts()) {
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
