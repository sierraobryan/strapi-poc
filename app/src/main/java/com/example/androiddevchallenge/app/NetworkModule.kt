package com.example.androiddevchallenge.app

import com.example.androiddevchallenge.ContentRepository
import com.example.androiddevchallenge.data.AuthenticationRepository
import com.example.androiddevchallenge.network.strapi.StrapiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://localhost:1337/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStrapiService(retrofit: Retrofit): StrapiService {
        return retrofit.create(StrapiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(): AuthenticationRepository {
        return AuthenticationRepository()
    }

    @Provides
    @Singleton
    fun provideContentRepository(
        service: StrapiService,
        authenticationRepository: AuthenticationRepository
    ): ContentRepository {
        return ContentRepository(service, authenticationRepository)
    }
}
