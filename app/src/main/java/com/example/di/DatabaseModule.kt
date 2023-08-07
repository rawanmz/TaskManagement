package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.TaskManagementDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TaskManagementDatabase{
        return Room.databaseBuilder(
            context,
            TaskManagementDatabase::class.java,
            "task_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: TaskManagementDatabase) = database.taskDao()

}