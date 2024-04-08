package com.example.marvelcleanarchitectureapp.modules.home.data.api.factory

import com.example.marvelcleanarchitectureapp.modules.home.data.api.model.CharactersResponse
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Characters
import com.example.myapplication.core.util.Result
import retrofit2.Response
import java.lang.Exception

class CharactersFactory: Factory<Characters>() {
    fun create(response: Response<CharactersResponse>): Result<Characters, Exception>{
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

    fun create(characters: List<Character>): Result<Characters, Exception>{
        if(characters.size > 0){
            return Result.success(Characters())
        }
        return create()
    }
}

private fun CharactersResponse.isDataValid(): Boolean {
    return !data?.results.isNullOrEmpty()
}

private fun CharactersResponse.toDataModel(): Characters {
    return Characters(

    )
}