package com.example.feature_dogs

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature_components.LazyFeed
import com.example.feature_components.customLazyStaggeredGridState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DogsLazyFeed(
    viewModel: DogViewModel = viewModel(),
    state: LazyStaggeredGridState = customLazyStaggeredGridState(key = "dogs")
) {
    LazyFeed(
        viewModel = viewModel,
        state = state
    )
}