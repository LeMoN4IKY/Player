package com.example.myapplication.domain

import androidx.room3.Delete
import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Insert
    suspend fun insertTrack(track: TrackEntity)

    @Query("SELECT * FROM playlist")
    fun getAllTracks(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM playlist WHERE isFavorite = 1")
    fun  getFavTracks():  Flow<List<TrackEntity>>

    @Query("SELECT * FROM playlist WHERE title LIKE :searchTitle")
    fun searchTitleFun(searchTitle: String): Flow<List<TrackEntity>>

    @Update
    suspend fun updateTrack(track: TrackEntity)

    @Delete
    suspend fun del(track: TrackEntity)
}