package com.example.todos.ui.list_todo

import com.example.todos.data.Todo

sealed class ToDoListEvent {
    data class onDeleteToDo(val todo: Todo) : ToDoListEvent()
    data class onDoneChange(val todo: Todo, val isDone: Boolean) : ToDoListEvent()
    object OnUndoDeleteClick : ToDoListEvent()
    data class onToDoClick(val todo: Todo) : ToDoListEvent()
    object onAddToDo : ToDoListEvent()
}