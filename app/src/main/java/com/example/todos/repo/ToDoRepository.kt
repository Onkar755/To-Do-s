package com.example.todos.repo

import com.example.todos.data.Todo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun insertToDo(todo: Todo)

    suspend fun deleteToDo(todo: Todo)

    suspend fun getToDoById(id: Int): Todo?

    fun getToDos(): Flow<List<Todo>>
}