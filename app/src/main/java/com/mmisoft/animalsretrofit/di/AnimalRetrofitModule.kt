package com.mmisoft.animalsretrofit.di

import com.mmisoft.animalsretrofit.data.api.AnimalApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimalApiModule{

    /**
     * provides the api instance
     */
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): AnimalApi{
        return builder
            .build()
            .create(AnimalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://zoo-animal-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
    }


}