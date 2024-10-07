package com.gcyazilim.cryptocrazycompose.di

import com.gcyazilim.cryptocrazycompose.repository.Repository
import com.gcyazilim.cryptocrazycompose.service.ApiService
import com.gcyazilim.cryptocrazycompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun injectRepository(api: ApiService): Repository = Repository(api)
}