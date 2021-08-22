package com.example.androiddevchallenge

import com.example.androiddevchallenge.data.AuthenticationRepository
import com.example.androiddevchallenge.data.model.Product
import com.example.androiddevchallenge.network.strapi.StrapiService
import retrofit2.Response
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val strapiService: StrapiService,
    private val authenticationRepository: AuthenticationRepository
)  {

    suspend fun getContent(): Response<List<Product>> {
        return strapiService.getContent("Bearer ${authenticationRepository.token}")
    }
}