package com.dicoding.kyosoappsubmission.ui.screen.anime_favorite

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
class AnimeFavoriteViewModel @Inject constructor(private val repo: AnimeRepo) : ViewModel() {
    private val animeFavorite = MutableStateFlow<
                            ResultCondition<
                                List<Anime>>>(
                                ResultCondition.LoadingState
                            )
    val animeFavoriteList = animeFavorite.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getFavoriteAnimeList()
                .catch { animeFavorite.value = ResultCondition.ErrorState(it.message.toString()) }
                .collect { animeFavorite.value = ResultCondition.SuccessState(it) }
        }
    }

    fun updateAnimeFavorite(id: Int, favorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateFavoriteAnime(id, favorite)
        }
    }
}