package com.example.marvelcleanarchitectureapp.modules.home.data.api.factory

import com.example.marvelcleanarchitectureapp.modules.home.data.api.model.CharactersResponse
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data
import com.example.myapplication.core.util.Result
import retrofit2.Response
import java.lang.Exception

class CharactersFactory: Factory<Data>() {
    fun create(response: Response<CharactersResponse>): Result<Data, Exception>{
        if(!response.isSuccessful){
            return create()
        }
        val body = response.body()
        body?.let {
            if(it.isDataValid()) {
                return Result.success(it.toDataModel())
            }
            return create()
        }
        return create()
    }

    fun create(characters: List<Character>): Result<Data, Exception>{
        if(characters.size > 0){
            return Result.success(Data(
                characters = characters.map {
                  Data.Character(
                      id = it.id,
                      name = it.name.orEmpty()
                  )
                },
                offset = 0,
                limit = 20,
            ))
        }
        return create()
    }
}

private fun CharactersResponse.isDataValid(): Boolean {
    return !data?.results.isNullOrEmpty()
}

private fun CharactersResponse.toDataModel(): Data {
    return Data(
        characters = data?.results?.map {
            Data.Character(
                id = it.id ?: 0,
                name = it.name.orEmpty()
            )
        } ?: emptyList(),
        offset = 0,
        limit = 20,
    )
}