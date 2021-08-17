package com.moneytransfer.core

import retrofit2.http.GET
import retrofit2.http.Query

interface CoreApi {

    @GET("END_POINT")
    suspend fun getApiData(): Any
}