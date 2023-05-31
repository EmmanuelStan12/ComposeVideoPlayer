package com.codedev.base

fun Int.getFormattedDuration(): String {
    val duration = this.toFloat() / 60000
    val minutes = duration.toInt()
    val builder = StringBuilder()
    if (minutes > 60) {
        builder.append(minutes / 60).append(":")
    }
    builder.append(minutes % 60).append(":")
    var seconds = ((duration - minutes) * 60).toInt().toString()
    if (seconds.length == 1)
        seconds = "0$seconds"
    builder.append(seconds)

    return builder.toString()
}