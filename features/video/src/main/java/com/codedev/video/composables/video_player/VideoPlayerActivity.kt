package com.codedev.home.video_player

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.codedev.home.VideoPlayerService
import com.codedev.home.databinding.ActivityVideoPlayerBinding
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors

class VideoPlayerActivity: ComponentActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var sessionToken: SessionToken
    private lateinit var controllerFuture: ListenableFuture<MediaController>

    private lateinit var controller: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        sessionToken = SessionToken(this, ComponentName(this, VideoPlayerService::class.java))

        controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
        controllerFuture.addListener(
            {
                controller = controllerFuture.get()
                binding.player.player = controller
            },
            MoreExecutors.directExecutor()
        )
    }
}