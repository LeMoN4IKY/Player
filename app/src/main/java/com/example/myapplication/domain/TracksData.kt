package com.example.myapplication.domain

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.room3.Entity
import androidx.room3.PrimaryKey



// Наш единый чемпионский источник истины для данных плеера
@Entity(tableName = "playlist")
data class TrackEntity(

    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    val title: String,
    val artist: String,
    val filelUri: Uri,
    val isFavorite: Boolean = false

)


val globalSongs = listOf("Песня 1", "Песня 2", "Песня 3")
val globalArtists = listOf("Исполнитель A", "Исполнитель Б", "Исполнитель В")
