package com.example.marvelcleanarchitectureapp.modules.home.data.api.retrofit
import com.example.marvelcleanarchitectureapp.modules.home.data.api.model.CharactersResponse
import com.example.marvelcleanarchitectureapp.utils.Keys
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20,
        @Query("ts") timestamp: String? = null,
        @Query("hash") hash: String? = null,
        @Query("apikey") apikey: String? = Keys.PUBLIC_KEY,
        ): Response<CharactersResponse>

}