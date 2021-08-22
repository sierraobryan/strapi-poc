package com.example.androiddevchallenge.network.strapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface StrapiService {

    @GET("products")
    suspend fun getContent(
        @Header("Authorization") authHeader: String
    ): Response<List<String>>
}