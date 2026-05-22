package com.example.composeinterface

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage


@Composable
fun MediaShowcaseScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 4.dp,
            shadowElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                AsyncImage(
                    model = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800",
                    contentDescription = "Featured landscape image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Watch the Scene",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Start
                )


                VideoPlayer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Nestled among towering peaks and serene valleys, this breathtaking mountain landscape " +
                            "captures the raw beauty of nature. The crisp alpine air, lush meadows, and snow-capped " +
                            "summits tell a story of timeless wonder — a reminder of how vast and magnificent our " +
                            "world truly is. Explore, breathe, and let the mountains speak.",
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Exit Screen")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Export Details")
                    }
                }

            }
        }
    }
}


@Composable
fun VideoPlayer(modifier: Modifier = Modifier) {
    // context in which it should open
    // context is pointed to the current activity
    val context = LocalContext.current

    // exoplayer :
    // android implementation of a video player
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            // define the src of the video
            val mediaItem = MediaItem.fromUri(
                Uri.parse(
                    "https://download.blender.org/durian/trailer/sintel_trailer-480p.mp4"
                )
            )
            // set the media item
            setMediaItem(mediaItem)
            // prepare
            prepare()
            // prevent default playing of video
            playWhenReady = false
        }
    }



    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                useController = true
            }
        },
        modifier = modifier
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MediaShowcaseScreenPreview() {
    MaterialTheme {
        MediaShowcaseScreen()
    }
}