package com.dicoding.kyosoappsubmission.data.repo

import com.dicoding.kyosoappsubmission.data.database.AnimeDAO
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import javax.inject.Inject

class AnimeRepo @Inject constructor(private val animeDao: AnimeDAO) {
    fun getAnimeList() = animeDao.getAnimeList()
    fun getAnimeDetail(id: Int) = animeDao.getAnimeDetail(id)
    fun searchAnime(anime: String) =  animeDao.searchAnime(anime)
    fun getFavoriteAnimeList() = animeDao.getFavoriteAnimeList()
    suspend fun insertAnimeList(animeList: List<Anime>) = animeDao.insertAnimeList(animeList)
    suspend fun updateFavoriteAnime(id: Int, favorite: Boolean) = animeDao.updateFavoriteAnime(id, favorite)
}