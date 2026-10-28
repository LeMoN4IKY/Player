package com.example.myapplication.domain

import androidx.room.TypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(entities = [TrackEntity::class], version = 1)
@TypeConverters(TrackConverters::class) // добавили конвертер
abstract class AppDatabase : RoomDatabase(){
    abstract fun trackdao(): TrackDao
}
