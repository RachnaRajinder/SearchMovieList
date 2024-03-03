package com.example.project_movieapp.domain.model

import com.example.project_movieapp.data.dto.Search
import com.squareup.moshi.Json

data class SearchResult(
    val search: List<Search>
)
