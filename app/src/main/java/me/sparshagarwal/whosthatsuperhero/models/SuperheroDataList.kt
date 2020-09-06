package me.sparshagarwal.whosthatsuperhero.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SuperheroDataList(
    @SerializedName("response") val response: String,
    @SerializedName("results-for") val results_for: String,
    @SerializedName("results") val results: ArrayList<SuperheroDataForList>
) : Serializable


data class SuperheroDataForList(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: Powerstats,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearance,
    @SerializedName("work") val work: Work,
    @SerializedName("connections") val connections: Connections,
    @SerializedName("image") val image: Image
) : Serializable