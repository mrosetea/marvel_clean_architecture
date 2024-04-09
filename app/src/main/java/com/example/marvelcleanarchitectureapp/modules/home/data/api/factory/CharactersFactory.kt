package com.example.marvelcleanarchitectureapp.modules.home.data.api.factory

import com.example.marvelcleanarchitectureapp.modules.home.data.api.model.CharactersResponse
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data
import com.example.myapplication.core.util.Result
import retrofit2.Response
import java.lang.Exception

class CharactersFactory: Factory<Data>() {
    fun create(characters: List<Character>, error: String? = null): Result<Data, Exception>{
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
                error = error
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