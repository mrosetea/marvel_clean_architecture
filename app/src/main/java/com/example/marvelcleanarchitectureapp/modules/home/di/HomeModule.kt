package com.example.marvelcleanarchitectureapp.modules.home.di

import com.example.marvelcleanarchitectureapp.modules.home.data.api.retrofit.CharactersApi
import com.example.marvelcleanarchitectureapp.modules.home.data.gateway.HomeGateway
import com.example.marvelcleanarchitectureapp.modules.home.data.gateway.HomeGatewayImpl
import com.example.marvelcleanarchitectureapp.utils.retrofit.BaseRetrofitFactory
import org.koin.dsl.module

val homeModule = module {
    single {
        BaseRetrofitFactory().create(CharactersApi::class.java)
    }
    single <HomeGateway> { HomeGatewayImpl(get(), get()) }
}