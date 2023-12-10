package com.example.data_dogs.api

import com.example.data_dogs.dto.DogImage

interface DogApiService {

    suspend fun getRandomImages(): List<DogImage>
}