package com.example.feature_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.core.FeedItem
import com.example.feature_components.utils.FeedImageFromUrl

@Composable
fun FeedLazyItem(
    modifier: Modifier = Modifier,
    item: FeedItem
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(R.dimen.spacing_extra_small),
                vertical = dimensionResource(R.dimen.spacing_extra_small)
            )
            .fillMaxWidth(),
        elevation = dimensionResource(R.dimen.elevation),
        shape = RoundedCornerShape(
            corner = CornerSize(dimensionResource(R.dimen.small_corner_radius))
        )
    ) {
        FeedImageFromUrl(modifier, item.url)
    }
}