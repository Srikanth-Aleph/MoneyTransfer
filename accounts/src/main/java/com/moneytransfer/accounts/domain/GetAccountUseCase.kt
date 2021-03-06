package com.moneytransfer.accounts.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moneytransfer.accounts.model.response.AccountItem
import com.moneytransfer.accounts.repository.AccountRepository
import com.moneytransfer.core.Response
import com.moneytransfer.core.Result
import com.moneytransfer.core.UseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class GetAccountUseCase(private val accountRepo: AccountRepository) :
    UseCase<LiveData<Result<List<AccountItem>>>>, UseCaseScope {
    override fun execute(): LiveData<Result<List<AccountItem>>> {
        val result = MutableLiveData<Result<List<AccountItem>>>()
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
