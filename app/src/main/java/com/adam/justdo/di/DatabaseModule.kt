package com.adam.justdo.di

import android.content.Context
import androidx.room.Room
import com.adam.justdo.data.local.room.AppDatabase
import com.adam.justdo.data.local.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTaskDao(taskDatabase: AppDatabase): TaskDao = taskDatabase.taskDao()

    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "task_database"
        ).build()
    }
}