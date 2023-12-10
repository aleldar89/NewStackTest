package com.example.feature_cats

import com.example.data_cats.dto.CatImage
import com.example.data_cats.use_cases.GetCatLocalDataUseCase
import com.example.data_cats.use_cases.GetCatRemoteDataUseCase
import com.example.feature_components.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CatViewModel @Inject constructor(
    private val catLocalDataUseCase: GetCatLocalDataUseCase,
    private val catRemoteDataUseCase: GetCatRemoteDataUseCase
) : BaseViewModel<CatImage>() {

    init {
        getImageRemoteData()
    }

    override fun getImageRemoteData() = doRequest {
        catRemoteDataUseCase.getCatRemoteData()
        getLocalData { catLocalDataUseCase.getCatLocalData() }
    }
}