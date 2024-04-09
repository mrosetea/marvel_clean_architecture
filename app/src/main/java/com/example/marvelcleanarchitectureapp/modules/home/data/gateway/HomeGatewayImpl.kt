package com.example.marvelcleanarchitectureapp.modules.home.data.gateway

import com.example.marvelcleanarchitectureapp.modules.home.data.api.factory.CharactersFactory
import com.example.marvelcleanarchitectureapp.modules.home.data.api.model.toCharacter
import com.example.marvelcleanarchitectureapp.modules.home.data.api.retrofit.CharactersApi
import com.example.marvelcleanarchitectureapp.modules.home.data.db.dao.CharacterDao
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data
import com.example.marvelcleanarchitectureapp.utils.Keys
import com.example.myapplication.core.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class HomeGatewayImpl(val charactersApi: CharactersApi, val characterDao: CharacterDao) :
    HomeGateway {
    override suspend fun getCharacters(offset: Int, limit: Int): Result<Data, Exception> =
        withContext(Dispatchers.IO) {

            val charactersFactory = CharactersFactory()
            return@withContext try {
                val timestamp = Keys.generateTimestamp()
                val hash = Keys.generateHash(timestamp)
                val response = charactersApi.getCharacters(offset, limit, timestamp, hash)
                response.body()?.data?.results
                    ?.map { it.toCharacter() }
                    ?.also { characterDao.upsertAll(it) }
                charactersFactory.create(characterDao.getCharacters())
            } catch (e: Exception) {
                charactersFactory.create(characterDao.getCharacters(), e.message)
            }
        }

    override suspend fun observeCharacters(): Flow<List<Character>> =
        characterDao.observeCharacters().flowOn(Dispatchers.IO)



}

fun test() = flow<Result<Data, Exception>> {
    //emit()
    //delay(2000)
    //emit()
}
    .flowOn(Dispatchers.IO)
    .catch {
        //emit()
    }