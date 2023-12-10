package com.example.data_cats.repository

import com.example.core.DtoRepository
import com.example.data_cats.dto.CatImage
import kotlinx.coroutines.flow.Flow

interface CatRepository : DtoRepository<CatImage>