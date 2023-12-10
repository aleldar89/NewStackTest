package com.example.data_dogs.use_cases

import com.example.data_dogs.dto.DogImage
import com.example.data_dogs.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDogLocalDataUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {
    suspend fun getDogLocalData(): Flow<List<DogImage>> = dogRepository.getLocalData()
}