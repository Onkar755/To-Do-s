package com.example.todos.ui.list_todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todos.data.Todo
import com.example.todos.repo.ToDoRepository
import com.example.todos.util.Routes
import com.example.todos.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val todos = repository.getToDos()

    private var deletedTodo: Todo? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ToDoListEvent) {
        when (event) {
            is ToDoListEvent.onToDoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }

            is ToDoListEvent.onAddToDo -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }

            is ToDoListEvent.onDeleteToDo -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteToDo(event.todo)
                }
                sendUiEvent(
                    UiEvent.ShowSnackbar(
                        message = "ToDo Deleted!",
                        action = "Undo"
                    )
                )
            }

            is ToDoListEvent.onDoneChange -> {
                viewModelScope.launch {
                    repository.insertToDo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

            is ToDoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertToDo(todo)
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}