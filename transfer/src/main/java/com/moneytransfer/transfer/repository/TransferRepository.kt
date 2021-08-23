package com.moneytransfer.transfer.repository

import com.moneytransfer.core.Response
import com.moneytransfer.transfer.model.TransferResponse

interface TransferRepository {
    suspend fun submitTransferRequest(): Response<TransferResponse>
}
