package me.sparshagarwal.whosthatsuperhero.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Powerstats(
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("strength") val strength: Int,
    @SerializedName("speed") val speed: Int,
    @SerializedName("durability") val durability: Int,
    @SerializedName("power") val power: Int,
    @SerializedName("combat") val combat: Int
) : Serializable

data class Biography(
    @SerializedName("full-name") val full_name: String,
    @SerializedName("alter-egos") val alter_egos: String,
    @SerializedName("aliases") val aliases: List<String>,
    @SerializedName("place-of-birth") val place_of_birth: String,
    @SerializedName("first-appearance") val first_appearance: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("alignment") val alignment: String
) : Serializable

data class Appearance(
    @SerializedName("gender") val gender: String,
    @SerializedName("race") val race: String,
    @SerializedName("height") val height: List<String>,
    @SerializedName("weight") val weight: List<String>,
    @SerializedName("eye-color") val eye_color: String,
    @SerializedName("hair-color") val hair_color: String
) : Serializable

data class Work(
    @SerializedName("occupation") val occupation: String,
    @SerializedName("base") val base: String
) : Serializable

data class Connections(
    @SerializedName("group-affiliation") val group_affiliation: String,
    @SerializedName("relatives") val relatives: String
) : Serializable

data class Image(
    @SerializedName("url") val url: String
) : Serializable