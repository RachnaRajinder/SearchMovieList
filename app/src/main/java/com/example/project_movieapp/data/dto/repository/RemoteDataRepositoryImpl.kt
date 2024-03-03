package com.example.project_movieapp.data.dto.repository

import com.example.project_movieapp.data.dto.MovieApi
import com.example.project_movieapp.data.dto.MoviedataDto
import com.example.project_movieapp.data.dto.SearchResultDto
import com.example.project_movieapp.domain.model.repository.RemoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val api: MovieApi
): RemoteDataRepository {
    override suspend fun getMovieData(title: String): MoviedataDto {
        return withContext(Dispatchers.Default){
            api.getMovieData(title = title)
        }
    }

    override suspend fun getSearchResult(search: String): SearchResultDto {
        return withContext(Dispatchers.Default){
            api.getSearchResult(search = search)
        }
    }
}