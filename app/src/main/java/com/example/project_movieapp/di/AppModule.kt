package com.example.project_movieapp.di

import com.example.project_movieapp.data.dto.MovieApi
import com.example.project_movieapp.data.dto.repository.RemoteDataRepositoryImpl
import com.example.project_movieapp.domain.model.repository.RemoteDataRepository
import com.example.project_movieapp.domain.model.use_case.GetMovieDataUseCase
import com.example.project_movieapp.domain.model.use_case.GetSearchResultUseCase
import com.example.project_movieapp.domain.model.use_case.UseCases
import com.example.project_movieapp.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideMovieApi(moshi: Moshi): MovieApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun providerRepository(api: MovieApi):RemoteDataRepository{
        return RemoteDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providerUseCases(repository: RemoteDataRepository): UseCases{
        return UseCases(
            getMovieDataUseCase = GetMovieDataUseCase(repository),
            getSearchResultUseCase = GetSearchResultUseCase(repository)

        )
    }
}