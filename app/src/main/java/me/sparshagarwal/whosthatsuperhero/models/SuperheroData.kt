package me.sparshagarwal.whosthatsuperhero.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SuperheroData(
    @SerializedName("response")val response: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: Powerstats,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearance,
    @SerializedName("work") val work: Work,
    @SerializedName("connections") val connections: Connections,
    @SerializedName("image") val image: Image
) : Serializable