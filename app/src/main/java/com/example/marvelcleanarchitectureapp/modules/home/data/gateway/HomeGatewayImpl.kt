package com.example.marvelcleanarchitectureapp.modules.home.data.gateway

import android.util.Log
import com.example.marvelcleanarchitectureapp.modules.home.data.api.factory.CharactersFactory
import com.example.marvelcleanarchitectureapp.modules.home.data.api.model.CharactersResponse
import com.example.marvelcleanarchitectureapp.modules.home.data.api.retrofit.CharactersApi
import com.example.marvelcleanarchitectureapp.modules.home.data.db.dao.CharacterDao
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Characters
import com.example.marvelcleanarchitectureapp.utils.Keys
import com.example.myapplication.core.util.Result
import retrofit2.Response
import java.lang.Exception

class HomeGatewayImpl(val charactersApi: CharactersApi, val characterDao: CharacterDao): HomeGateway {

    suspend fun populateDatabase(response: Response<CharactersResponse>){
        characterDao.deleteAll()
        val toInsert: List<Character> = response.body()?.data?.results?.map {
            Character(it.id ?: 0, it.name.orEmpty())
        } ?: emptyList()
        characterDao.insertAll(toInsert)
    }

    override suspend fun getCharacters(forceUpdate: Boolean): Result<Characters, Exception> {
        val charactersFactory = CharactersFactory()
        try {
            return if(forceUpdate){
                val timestamp = Keys.generateTimestamp()
                val hash = Keys.generateHash(timestamp)
                val response = charactersApi.getCharacters(timestamp, hash)
                populateDatabase(response)
                charactersFactory.create(response)
            } else {
                val characatersInDatabase = characterDao.getCharacters()
                charactersFactory.create(characatersInDatabase)
            }
        } catch (e: Exception) {
            val characatersInDatabase = characterDao.getCharacters()
            return charactersFactory.create(characatersInDatabase)
        }
    }
}