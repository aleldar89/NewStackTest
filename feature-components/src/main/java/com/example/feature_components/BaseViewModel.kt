package com.example.feature_components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.FeedItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<D: FeedItem> : ViewModel() {

    private val _imageLocalData = MutableStateFlow<List<D>>(emptyList())
    val imageLocalData: StateFlow<List<D>>
        get() = _imageLocalData

    abstract fun getImageRemoteData(): Job

    protected fun getLocalData(
        request: suspend () -> Flow<List<D>>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            request().collect {
                _imageLocalData.value = it
            }
        }
    }

    protected fun doRequest(
        request: suspend () -> Unit
    ) = try {
        viewModelScope.launch(Dispatchers.IO) {
            request()
        }
    } catch (e: Exception) {
        throw e
    }
}