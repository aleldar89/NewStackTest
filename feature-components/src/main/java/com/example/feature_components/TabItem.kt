package com.example.feature_components

import androidx.compose.runtime.Composable

data class TabItem (
    val title: String,
    val screen: @Composable () -> Unit
)