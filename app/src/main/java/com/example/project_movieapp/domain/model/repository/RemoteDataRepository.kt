package com.example.project_movieapp.domain.model.repository
import com.example.project_movieapp.data.dto.MoviedataDto
import com.example.project_movieapp.data.dto.SearchResultDto

interface RemoteDataRepository {

    suspend fun getMovieData(title: String): MoviedataDto

    suspend fun getSearchResult(search: String): SearchResultDto
}