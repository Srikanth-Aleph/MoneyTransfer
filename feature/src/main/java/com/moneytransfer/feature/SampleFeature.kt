package com.moneytransfer.feature

import com.moneytransfer.core.SampleCore

object SampleFeature {
    fun getFromCoreModule(): String {
        return SampleCore.someCoreFunction("arg1", "arg2", "arg3")
    }
}