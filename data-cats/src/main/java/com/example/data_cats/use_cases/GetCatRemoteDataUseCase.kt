package com.example.data_cats.use_cases

import com.example.data_cats.repository.CatRepository
import javax.inject.Inject

class GetCatRemoteDataUseCase @Inject constructor(
    private val catRepository: CatRepository
) {
    suspend fun getCatRemoteData() = catRepository.getRemoteData()
}