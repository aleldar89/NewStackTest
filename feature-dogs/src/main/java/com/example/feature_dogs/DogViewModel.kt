package com.example.feature_dogs

import com.example.data_dogs.dto.DogImage
import com.example.data_dogs.use_cases.GetDogLocalDataUseCase
import com.example.data_dogs.use_cases.GetDogRemoteDataUseCase
import com.example.feature_components.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogLocalDataUseCase: GetDogLocalDataUseCase,
    private val dogRemoteDataUseCase: GetDogRemoteDataUseCase
) : BaseViewModel<DogImage>() {

    init {
        getImageRemoteData()
    }

    override fun getImageRemoteData() = doRequest {
        dogRemoteDataUseCase.getDogRemoteData()
        getLocalData { dogLocalDataUseCase.getDogLocalData() }
    }
}