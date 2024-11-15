package com.example.todos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todo: Todo)

    @Delete
    suspend fun deleteToDo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getToDoById(id: Int): Todo?

    @Query("SELECT * FROM todo")
    fun getToDos(): Flow<List<Todo>>
}