package com.example.data_dogs.api

import com.example.data_dogs.dto.DogImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class DogApiServiceImpl @Inject constructor(
    private val ktorClient: HttpClient
) : DogApiService {

    private val baseUrl = "https://api.thedogapi.com/v1/images/"
    private val request: String = "search?limit=10"

    override suspend fun getRandomImages(): List<DogImage> =
        ktorClient.get(baseUrl + request).body()
}