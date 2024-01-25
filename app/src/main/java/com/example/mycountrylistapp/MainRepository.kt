package com.example.mycountrylistapp

import com.example.mycountrylistapp.Network.MyAPI

class MainRepository constructor(private val retrofitService: MyAPI) {
    suspend fun getAllCountries() = retrofitService.getAllCountries()
}