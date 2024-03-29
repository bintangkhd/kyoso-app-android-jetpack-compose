package com.dicoding.kyosoappsubmission.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDAO {
    @Query("SELECT * FROM anime")
    fun getAnimeList(): Flow<List<Anime>>

    @Query("SELECT * FROM anime WHERE id = :id")
    fun getAnimeDetail(id: Int): Flow<Anime>

    @Query("SELECT * FROM anime WHERE name LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%' OR synopsis LIKE '%' || :query || '%'")
    fun searchAnime(query: String): Flow<List<Anime>>

    @Query("SELECT * FROM anime WHERE favorite = 1")
    fun getFavoriteAnimeList(): Flow<List<Anime>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(animeList: List<Anime>)

    @Query("UPDATE anime SET favorite = :favorite WHERE id = :id")
    suspend fun updateFavoriteAnime(id: Int, favorite: Boolean)
}