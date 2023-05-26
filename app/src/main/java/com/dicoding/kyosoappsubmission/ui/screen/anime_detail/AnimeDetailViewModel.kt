package com.dicoding.kyosoappsubmission.ui.screen.anime_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import com.dicoding.kyosoappsubmission.data.repo.AnimeRepo
import com.dicoding.kyosoappsubmission.data.repo.ResultCondition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(private val repo: AnimeRepo) : ViewModel() {
    private val anime = MutableStateFlow<
                            ResultCondition<Anime>>(
                                ResultCondition.LoadingState
                            )
    val animeDetail = anime.asStateFlow()

    fun getDetailAnime(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAnimeDetail(id)
                .catch { anime.value = ResultCondition.ErrorState(it.message.toString()) }
                .collect { anime.value = ResultCondition.SuccessState(it) }
        }
    }

    fun updatesAnimeFavorite(id: Int, favorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateFavoriteAnime(id, favorite)
        }
    }
}