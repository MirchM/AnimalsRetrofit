package com.mmisoft.animalsretrofit.data.api

import com.mmisoft.animalsretrofit.data.api.model.Animal
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * interface used to get the data from the api
 */
interface AnimalApi {

    /**
     * gets random Animal objects from the api in JSON format
     * @param amount the amount of animal objects to be retrieved from the server
     */
    @GET("animals/rand/{number}")
    suspend fun getAnimals(
        @Path(value = "number", encoded = true) amount: Int
    ): List<Animal>
}