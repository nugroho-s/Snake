package me.nugsky.snake

var refreshCounter = 0
var previousTimestamp = System.currentTimeMillis()

fun calculateFps(): Int {
    refreshCounter += 1
    val duration = (System.currentTimeMillis() - previousTimestamp) / 1000
    if (duration == 0L) {
        return 0
    }
    if (duration > 5) {
        println(refreshCounter / duration)
        refreshCounter = 0
        previousTimestamp = System.currentTimeMillis()
    }
    return (refreshCounter / duration).toInt()
}