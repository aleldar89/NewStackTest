package com.example.feature_cats

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature_components.LazyFeed
import com.example.feature_components.customLazyStaggeredGridState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CatsLazyFeed(
    viewModel: CatViewModel = viewModel(),
    state: LazyStaggeredGridState = customLazyStaggeredGridState(key = "cats")
) {
    LazyFeed(
        viewModel = viewModel,
        state = state
    )
}