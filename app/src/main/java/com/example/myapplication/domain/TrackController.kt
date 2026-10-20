package com.example.myapplication.domain

// Чистая математика переключения треков по SOLID — без Compose и вёрстки!
fun getNextIndex(currentIndex: Int, listSize: Int): Int {
    return if (currentIndex < listSize - 1) currentIndex + 1 else 0
}

fun getPrevIndex(currentIndex: Int, listSize: Int): Int {
    return if (currentIndex > 0) currentIndex - 1 else listSize - 1
}
