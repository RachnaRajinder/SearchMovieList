package com.example.project_movieapp.data.dto


import com.example.project_movieapp.domain.model.SearchResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultDto(
    @Json(name = "Search")
    val search: List<Search>,
    @Json(name = "totalResults")
    val totalResults: String,
    @Json(name = "Response")
    val response: String
)

fun SearchResultDto.toSearchResult(): SearchResult{
    return SearchResult(search = search)
}