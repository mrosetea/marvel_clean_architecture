package com.example.marvelcleanarchitectureapp.modules.home.data.gateway

import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data
import com.example.myapplication.core.util.Result
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

interface HomeGateway {
    suspend fun getCharacters(forceUpdate: Boolean = false): Result<Data, Exception>

    suspend fun observeCharacters(): Flow<List<Character>>

}