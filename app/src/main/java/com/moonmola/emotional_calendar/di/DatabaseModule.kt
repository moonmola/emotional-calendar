package com.moonmola.emotional_calendar.di

import android.content.Context
import androidx.room.Room
import com.moonmola.emotional_calendar.data.AppDatabase
import com.moonmola.emotional_calendar.data.DiaryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "diary.db"
        ).build()
    }

    @Provides
    fun providePlantDao(appDatabase: AppDatabase): DiaryDao {
        return appDatabase.diaryDao()
    }

}