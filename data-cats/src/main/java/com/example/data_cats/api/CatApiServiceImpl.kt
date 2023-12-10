package com.example.data_cats.api

import com.example.data_cats.dto.CatImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class CatApiServiceImpl @Inject constructor(
    private val ktorClient: HttpClient
) : CatApiService {

    private val baseUrl = "https://api.thecatapi.com/v1/images/"
    private val request: String = "search?limit=10"

    override suspend fun getRandomImages(): List<CatImage> =
        ktorClient.get(baseUrl + request).body()
}