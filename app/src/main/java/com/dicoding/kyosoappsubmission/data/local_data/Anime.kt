package com.dicoding.kyosoappsubmission.data.local_data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class Anime(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val photoUrl: String,
    val author: String,
    val releaseDate: String,
    val ranked: String,
    val rating: Double,
    val reviewers: Int,
    val synopsis: String,
    val favorite: Boolean = false
)
