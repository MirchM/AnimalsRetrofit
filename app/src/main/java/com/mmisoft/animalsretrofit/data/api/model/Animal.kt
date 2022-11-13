package com.mmisoft.animalsretrofit.data.api.model


import com.google.gson.annotations.SerializedName

/**
 * a data class that hold the JSON data received by the api.
 * It implements serializable so it can be sent to another compose destination
 */
data class Animal(
    @SerializedName("active_time")
    val activeTime: String,
    @SerializedName("animal_type")
    val animalType: String,
    @SerializedName("diet")
    val diet: String,
    @SerializedName("geo_range")
    val geoRange: String,
    @SerializedName("habitat")
    val habitat: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_link")
    val imageLink: String,
    @SerializedName("latin_name")
    val latinName: String,
    @SerializedName("length_max")
    val lengthMax: String,
    @SerializedName("length_min")
    val lengthMin: String,
    @SerializedName("lifespan")
    val lifespan: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("weight_max")
    val weightMax: String,
    @SerializedName("weight_min")
    val weightMin: String
): java.io.Serializable