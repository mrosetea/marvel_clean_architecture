package com.example.marvelcleanarchitectureapp.modules.home.data.gateway

import com.example.marvelcleanarchitectureapp.modules.home.data.model.Characters
import com.example.myapplication.core.util.Result
import java.lang.Exception

interface HomeGateway {
    suspend fun getCharacters(forceUpdate: Boolean = false): Result<Characters, Exception>
}