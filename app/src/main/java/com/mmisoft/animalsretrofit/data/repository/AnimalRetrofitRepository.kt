package com.mmisoft.animalsretrofit.data.repository

import com.mmisoft.animalsretrofit.data.api.AnimalApi
import com.mmisoft.animalsretrofit.data.api.model.Animal
import javax.inject.Inject

class AnimalRetrofitRepository @Inject constructor(
    private val animalApi: AnimalApi
) {
    suspend fun getAnimals(amount: Int): List<Animal> {
        return animalApi.getAnimals(amount = amount)
    }
}