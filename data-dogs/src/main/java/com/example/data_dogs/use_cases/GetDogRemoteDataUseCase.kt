package com.example.data_dogs.use_cases

import com.example.data_dogs.repository.DogRepository
import javax.inject.Inject

class GetDogRemoteDataUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {
    suspend fun getDogRemoteData() = dogRepository.getRemoteData()
}