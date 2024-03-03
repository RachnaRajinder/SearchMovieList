package com.example.project_movieapp.domain.model.use_case

import com.example.project_movieapp.data.dto.SearchResultDto
import com.example.project_movieapp.domain.model.repository.RemoteDataRepository
import kotlinx.coroutines.flow.flow
import  kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {
    suspend operator fun invoke(search: String): Flow<SearchResultDto> = flow{
        emit(repository.getSearchResult(search))
    }
}