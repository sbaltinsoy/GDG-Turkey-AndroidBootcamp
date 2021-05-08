package com.sbaltinsoy.gdg_bitirmeproje.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.ratesapi.io/"


private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface DovizApiService {
    @GET("api/latest?symbols=USD,GBP,TRY")
    fun getProperties():
            Call<CurrencyProperty>
}

object DovizApi {
    val retrofitService : DovizApiService by lazy {
        retrofit.create(DovizApiService::class.java) }
}