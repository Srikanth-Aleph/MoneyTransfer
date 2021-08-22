package com.moneytransfer.accounts.domain

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * A default implementation for getting a coroutine scope in a [com.aleph_labs.cleanarch.UseCase].
 *
 * **USE WITH CAUTION - You must call `coroutineContext.cancel()` when your Use Case cancels.**
 *
 */
interface UseCaseScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate
}
