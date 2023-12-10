package com.example.data_cats.api

import com.example.data_cats.dto.CatImage

interface CatApiService {

    suspend fun getRandomImages(): List<CatImage>
}