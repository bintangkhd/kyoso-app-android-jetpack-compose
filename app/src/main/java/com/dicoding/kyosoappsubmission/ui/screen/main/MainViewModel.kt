package com.dicoding.kyosoappsubmission.ui.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import com.dicoding.kyosoappsubmission.data.local_data.AnimeFakeData
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
class MainViewModel @Inject constructor(private val repo: AnimeRepo) : ViewModel() {

    data class Home(val query: String = "")

    private val listAnime = MutableStateFlow<
                                ResultCondition<
                                    List<Anime>>>(
                                    ResultCondition.LoadingState
                                )
    val animeList = listAnime.asStateFlow()

    private val home = mutableStateOf(Home())
    val homeState: State<Home> = home

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAnimeList().collect { anime ->
                when (anime.isEmpty()) {
                    true -> repo.insertAnimeList(AnimeFakeData.fakeData)
                    else -> listAnime.value = ResultCondition.SuccessState(anime)
                }
            }
        }
    }

    private fun searchAnime(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.searchAnime(query)
                .catch { listAnime.value = ResultCondition.ErrorState(it.message.toString()) }
                .collect { listAnime.value = ResultCondition.SuccessState(it) }
        }
    }

    fun onQueryChange(query: String) {
        home.value = home.value.copy(query = query)
        searchAnime(query)
    }
}

