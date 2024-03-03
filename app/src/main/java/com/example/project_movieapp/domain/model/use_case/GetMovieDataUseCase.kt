package com.example.project_movieapp.domain.model.use_case

import android.icu.text.CaseMap.Title
import com.example.project_movieapp.data.dto.MoviedataDto
import com.example.project_movieapp.domain.model.repository.RemoteDataRepository
import kotlinx.coroutines.flow.flow
import  kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDataUseCase @Inject constructor(
    private val repository: RemoteDataRepository
){
    suspend operator fun invoke(title: String): Flow<MoviedataDto> = flow {
        emit(repository.getMovieData(title))
    }
}