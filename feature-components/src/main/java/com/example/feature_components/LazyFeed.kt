package com.example.feature_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.FeedItem

@Composable
fun <D : FeedItem> LazyFeed(
    viewModel: BaseViewModel<D>,
    state: LazyStaggeredGridState,
) {
    val feed by viewModel.imageLocalData.collectAsStateWithLifecycle()

    if (!state.canScrollForward) {
        viewModel.getImageRemoteData()
    }

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = StaggeredGridCells.Adaptive(minSize = dimensionResource(R.dimen.grid_cell_space)),
        verticalItemSpacing = dimensionResource(R.dimen.vertical_item_space),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.vertical_item_space)),
        state = state
    ) {
        items(
            items = feed,
            key = { item ->
                item.id
            },
            contentType = { it.javaClass }
        ) {
            FeedLazyItem(item = it)
        }
    }
}

private val saveMap = mutableMapOf<String, KeyParams>()

private data class KeyParams(
    val params: String = "",
    val index: Int,
    val scrollOffset: Int
)

@Composable
fun customLazyStaggeredGridState(
    key: String,
    params: String = "",
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0
): LazyStaggeredGridState {
    val scrollState = rememberSaveable(saver = LazyStaggeredGridState.Saver) {
        var savedValue = saveMap[key]
        if (savedValue?.params != params) savedValue = null
        val savedIndex = savedValue?.index ?: initialFirstVisibleItemIndex
        val savedOffset = savedValue?.scrollOffset ?: initialFirstVisibleItemScrollOffset
        LazyStaggeredGridState(
            savedIndex,
            savedOffset
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            val lastIndex = scrollState.firstVisibleItemIndex
            val lastOffset = scrollState.firstVisibleItemScrollOffset
            saveMap[key] = KeyParams(params, lastIndex, lastOffset)
        }
    }
    return scrollState
}