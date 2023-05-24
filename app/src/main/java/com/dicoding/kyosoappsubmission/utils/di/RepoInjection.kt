package com.dicoding.kyosoappsubmission.utils.di

import com.dicoding.kyosoappsubmission.data.database.AnimeDAO
import com.dicoding.kyosoappsubmission.data.repo.AnimeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepoInjection {

    @Provides
    @ViewModelScoped
    fun provideRepo(animeDao: AnimeDAO): AnimeRepo {
        return AnimeRepo(animeDao)
    }

}