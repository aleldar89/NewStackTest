package com.example.data_cats.use_cases

import com.example.data_cats.dto.CatImage
import com.example.data_cats.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatLocalDataUseCase @Inject constructor(
    private val catRepository: CatRepository
) {
    suspend fun getCatLocalData(): Flow<List<CatImage>> = catRepository.getLocalData()
}