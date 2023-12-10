package com.example.newstacktest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.feature_cats.CatsLazyFeed
import com.example.feature_components.TabItem
import com.example.feature_dogs.DogsLazyFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout() {

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    ) {
        tabs.size
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

            FeedTabRow(
                tabs = tabs,
                pagerState = pagerState,
                coroutineScope = coroutineScope
            )

            FeedHorizontalPager(
                tabs = tabs,
                pagerState = pagerState
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedTabRow(
    tabs: List<TabItem>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) {
    TabRow(selectedTabIndex = pagerState.currentPage) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                selected = index == pagerState.currentPage,
                text = { Text(text = tabItem.title) },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedHorizontalPager(
    tabs: List<TabItem>,
    pagerState: PagerState
) {
    HorizontalPager(
        modifier = Modifier,
        state = pagerState,
        pageContent = {
            tabs[pagerState.currentPage].screen()
        },
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        )
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
val tabs = listOf(
    TabItem(
        title = "Cats",
        screen = { CatsLazyFeed() }
    ),
    TabItem(
        title = "Dogs",
        screen = { DogsLazyFeed() }
    )
)