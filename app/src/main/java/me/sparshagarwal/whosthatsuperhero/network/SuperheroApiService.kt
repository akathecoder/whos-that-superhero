package me.sparshagarwal.whosthatsuperhero.network

import me.sparshagarwal.whosthatsuperhero.models.SuperheroData
import me.sparshagarwal.whosthatsuperhero.models.SuperheroDataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiService {

    @GET("{character-id}")
    fun getDataById(
        @Path("character-id") characterId: String
    ):Call<SuperheroData>

    @GET("search/{name}/")
    fun getDataBySearch(
        @Path("name") name: String
    ):Call<SuperheroDataList>

}