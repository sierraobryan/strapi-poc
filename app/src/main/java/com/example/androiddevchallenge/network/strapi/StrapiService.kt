package com.example.androiddevchallenge.network.strapi

import com.example.androiddevchallenge.data.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface StrapiService {

    @GET("products")
    suspend fun getContent(
        @Header("Authorization") authHeader: String
    ): Response<List<Product>>
}