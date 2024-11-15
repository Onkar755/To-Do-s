package com.example.todos.di

import android.app.Application
import androidx.room.Room
import com.example.todos.data.ToDoDatabase
import com.example.todos.repo.ToDoRepository
import com.example.todos.repo.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToDoAppModule {

    @Provides
    @Singleton
    fun providesToDoDatabase(application: Application): ToDoDatabase {
        return Room.databaseBuilder(
            application,
            ToDoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesToDoRepository(database: ToDoDatabase): ToDoRepository {
        return ToDoRepositoryImpl(database.dao)
    }
}