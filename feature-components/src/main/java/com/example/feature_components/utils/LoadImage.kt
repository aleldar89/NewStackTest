package com.example.feature_components.utils

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.feature_components.R

@Composable
fun FeedImageFromUrl(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String = "",
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.FillWidth,
    placeholder: Painter = painterResource(id = R.drawable.dog_icon),
) {
    val imageResource = loadImage(url = url)

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = alignment
    ) {
        if (imageResource is ImageBitmap) {
            Image(
                bitmap = imageResource as ImageBitmap,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
            )
        } else {
            Image(
                painter = placeholder,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun loadImage(url: String): Any? {
    val context = LocalContext.current
    var imageResource by remember { mutableStateOf<Any?>(null) }
    
    LaunchedEffect(url) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    imageResource = resource.toBitmap().asImageBitmap()
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }
    return imageResource
}