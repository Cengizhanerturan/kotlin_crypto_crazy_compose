package com.gcyazilim.cryptocrazycompose.service

import com.gcyazilim.cryptocrazycompose.model.Crypto
import com.gcyazilim.cryptocrazycompose.model.CryptoList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cryptolist.json")
    suspend fun getCryptoList(): Response<CryptoList>

    @GET("crypto.json")
    suspend fun getCrypto(): Response<Crypto>
}