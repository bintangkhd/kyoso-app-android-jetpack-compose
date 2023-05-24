package com.dicoding.kyosoappsubmission.utils.di

import android.app.Application
import androidx.room.Room
import com.dicoding.kyosoappsubmission.data.database.AnimeDAO
import com.dicoding.kyosoappsubmission.data.database.AnimeDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseInjection {

    @Provides
    @Singleton
    fun provideDB(application: Application): AnimeDB {
      return Room
          .databaseBuilder(application, AnimeDB::class.java, "kyoso.db")
          .fallbackToDestructiveMigration()
          .build()

    }

    @Provides
    fun provideDAO(db: AnimeDB): AnimeDAO {
        return db.animeDao()
    }

}