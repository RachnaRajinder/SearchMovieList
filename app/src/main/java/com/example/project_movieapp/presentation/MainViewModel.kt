package com.example.project_movieapp.presentation

import android.icu.text.CaseMap.Title
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_movieapp.data.dto.toMovieData
import com.example.project_movieapp.data.dto.toSearchResult
import com.example.project_movieapp.domain.model.MovieData
import com.example.project_movieapp.domain.model.SearchResult
import com.example.project_movieapp.domain.model.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.example.project_movieapp.util.Result
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
     private val _movieDataResponse:MutableStateFlow<Result<MovieData>> =
         MutableStateFlow<Result<MovieData>>(Result.Idle)

    val movieDataResponse: StateFlow<Result<MovieData>> =
        _movieDataResponse.asStateFlow()

    private val _searchResponse : MutableStateFlow<Result<SearchResult>> =
        MutableStateFlow<Result<SearchResult>>(Result.Idle)
    val searchResult : StateFlow<Result<SearchResult>> = _searchResponse.asStateFlow()

    fun getMovieData(
        title: String
    ) = viewModelScope .launch {
        useCases.getMovieDataUseCase(title)
            .onStart {
                _movieDataResponse.value = Result.Loading
            }.catch {
                _movieDataResponse.value = Result.Error(it)
            }.collect{
                val result:MovieData = it.toMovieData()
                _movieDataResponse.value = Result.Success(result)
            }
    }
    fun getSearchData(
        search: String
    ) = viewModelScope.launch {
        useCases.getSearchResultUseCase(search)
            .onStart {
                _searchResponse.value = Result.Loading
            }.catch {
                _searchResponse.value = Result.Error(it)
            }.collect{
                val result: SearchResult = it.toSearchResult()
                _searchResponse.value = Result.Success(result)
            }
    }
}