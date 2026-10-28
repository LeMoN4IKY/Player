package com.example.myapplication.domain

import android.R.attr.track
import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query

@Dao
interface TrackDao {

    @Insert
    suspend fun insertTrack(track: TrackEntity)

    @Query("SELECT * FROM playlist")
    suspend fun getAllTracks(): List<TrackEntity>
}