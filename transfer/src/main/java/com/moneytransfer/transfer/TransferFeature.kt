package com.moneytransfer.transfer

import com.moneytransfer.core.SampleCore

object TransferFeature {
    fun getFromTransferModule(): String {
        return SampleCore.getFromCoreFunction("This is", "transfer", "module")
    }
}