package com.codedev.room_lib.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
