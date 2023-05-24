package com.dicoding.kyosoappsubmission.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.kyosoappsubmission.data.local_data.Anime

@Database(entities = [Anime::class], version = 1, exportSchema = false)
abstract class AnimeDB : RoomDatabase() {
    abstract fun animeDao(): AnimeDAO
}