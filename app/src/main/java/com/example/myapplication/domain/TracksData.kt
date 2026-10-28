package com.example.myapplication.domain

import android.net.Uri
import androidx.room3.Entity
import androidx.room3.PrimaryKey


// Наш единый чемпионский источник истины для данных плеера
@Entity(tableName = "playlist")
data class TrackEntity(

    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    val title: String,
    val artist: String,
    val fuelUri: Uri

)