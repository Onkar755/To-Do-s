package com.example.todos.repo

import com.example.todos.data.ToDoDao
import com.example.todos.data.Todo
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(
    private val todoDao: ToDoDao
) : ToDoRepository {
    override suspend fun insertToDo(todo: Todo) {
        todoDao.insertToDo(todo)
    }

    override suspend fun deleteToDo(todo: Todo) {
        todoDao.deleteToDo(todo)
    }

    override suspend fun getToDoById(id: Int): Todo? {
        return todoDao.getToDoById(id)
    }

    override fun getToDos(): Flow<List<Todo>> {
        return todoDao.getToDos()
    }
}