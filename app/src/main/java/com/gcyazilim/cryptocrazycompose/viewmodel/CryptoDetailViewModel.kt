package com.gcyazilim.cryptocrazycompose.viewmodel

import androidx.lifecycle.ViewModel
import com.gcyazilim.cryptocrazycompose.model.Crypto
import com.gcyazilim.cryptocrazycompose.repository.Repository
import com.gcyazilim.cryptocrazycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {

    suspend fun getCrypto(): Resource<Crypto> {
        return repository.getCrypto()
    }
}