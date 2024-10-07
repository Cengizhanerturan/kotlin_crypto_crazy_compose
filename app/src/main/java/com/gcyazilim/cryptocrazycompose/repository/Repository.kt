package com.gcyazilim.cryptocrazycompose.repository

import com.gcyazilim.cryptocrazycompose.model.Crypto
import com.gcyazilim.cryptocrazycompose.model.CryptoList
import com.gcyazilim.cryptocrazycompose.service.ApiService
import com.gcyazilim.cryptocrazycompose.util.Resource
import okhttp3.Response
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val api: ApiService
) {
    suspend fun getCryptoList(): Resource<CryptoList> {
        try {
            val response = api.getCryptoList()
            if (response.isSuccessful) {
                response.body()?.let { cryptoList ->
                    return Resource.Success(cryptoList)
                }
            }
            return Resource.Error("Error!")
        } catch (e: Exception) {
            return Resource.Error("Error!")
        }
    }

    suspend fun getCrypto(): Resource<Crypto> {
        try {
            val response = api.getCrypto()
            if (response.isSuccessful) {
                response.body()?.let { crypto ->
                    return Resource.Success(crypto)
                }
            }
            return Resource.Error("Error!")
        } catch (e: Exception) {
            return Resource.Error("Error!")
        }
    }
}