package com.example.mycountrylistapp.Network

import com.example.mycountrylistapp.data.Countries
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyAPI {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getAllCountries(): Response<List<Countries>>

    companion object {
        var retrofitService: MyAPI? = null
        fun getInstance(): MyAPI {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MyAPI::class.java)
            }
            return retrofitService!!
        }

    }
}