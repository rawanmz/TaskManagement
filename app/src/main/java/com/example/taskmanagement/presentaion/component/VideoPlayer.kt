package com.example.taskmanagement.presentaion.component

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun VideoPlayer(url: String) {
    Box(
        Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val exoPlayer = ExoPlayer.Builder(context).build()
        val mediaItem =
            MediaItem.fromUri(Uri.parse((url)))
        exoPlayer.setMediaItem(mediaItem)
        val playerView = StyledPlayerView(context)
        playerView.player = exoPlayer
        DisposableEffect(
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { playerView })
        ) {
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            onDispose {
                exoPlayer.release()
            }
        }
    }
}