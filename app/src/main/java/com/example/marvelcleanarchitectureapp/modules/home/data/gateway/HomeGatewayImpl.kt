package com.example.marvelcleanarchitectureapp.modules.home.data.gateway

import com.example.marvelcleanarchitectureapp.modules.home.data.api.factory.CharactersFactory
import com.example.marvelcleanarchitectureapp.modules.home.data.api.model.CharactersResponse
import com.example.marvelcleanarchitectureapp.modules.home.data.api.retrofit.CharactersApi
import com.example.marvelcleanarchitectureapp.modules.home.data.db.dao.CharacterDao
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data
import com.example.marvelcleanarchitectureapp.utils.Keys
import com.example.myapplication.core.util.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.lang.Exception

class HomeGatewayImpl(val charactersApi: CharactersApi, val characterDao: CharacterDao): HomeGateway {

    suspend fun populateDatabase(response: Response<CharactersResponse>) {
        //characterDao.deleteAll()
        val toInsert: List<Character> = response.body()?.data?.results?.map {
            Character(it.id ?: 0, it.name.orEmpty())
        } ?: emptyList()
        characterDao.insertAll(toInsert)
    }

    override suspend fun getCharacters(forceUpdate: Boolean): Result<Data, Exception> {
        val charactersFactory = CharactersFactory()
        val characatersInDatabase = characterDao.getCharacters()
        try {
            return if (forceUpdate || characatersInDatabase.size == 0) {
                val timestamp = Keys.generateTimestamp()
                val hash = Keys.generateHash(timestamp)
                val response = charactersApi.getCharacters(offset = 0, limit = 20, timestamp, hash,)
                populateDatabase(response)
                charactersFactory.create(response)
            } else {

                charactersFactory.create(characatersInDatabase)
            }
        } catch (e: Exception) {
            val characatersInDatabase = characterDao.getCharacters()
            return charactersFactory.create(characatersInDatabase)
        }
    }

    override suspend fun observeCharacters(): Flow<List<Character>> =
        characterDao.observeCharacters()

}