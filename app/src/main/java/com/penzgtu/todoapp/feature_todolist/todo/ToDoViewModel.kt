package com.penzgtu.todoapp.feature_todolist.todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.use_case.ToDoUseCases
import com.penzgtu.todoapp.feature_todolist.domain.util.OrderType
import com.penzgtu.todoapp.feature_todolist.domain.util.ToDoOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val toDoUseCases: ToDoUseCases
) : ViewModel(){

    private val _state = mutableStateOf(ToDoState())
    val state: State<ToDoState> = _state

    private var recentlyDeletedToDo: ToDoList? = null

    private var getToDoJob: Job? = null

    init {
        getToDo(ToDoOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: ToDoEvent){
        when(event) {
            is ToDoEvent.Order -> {
                if(state.value.toDoOrder::class == event.toDoOrder::class &&
                        state.value.toDoOrder.orderType == event.toDoOrder.orderType
                ){
                    return
                }
                getToDo(event.toDoOrder)

            }
            is ToDoEvent.DeleteToDo -> {
                viewModelScope.launch {
                    toDoUseCases.deleteToDo(event.toDoList)
                    recentlyDeletedToDo = event.toDoList
                }
            }
            is ToDoEvent.RestoreToDo -> {
                viewModelScope.launch {
                    toDoUseCases.addToDo(recentlyDeletedToDo ?: return@launch)
                    recentlyDeletedToDo = null
                }

            }
            is ToDoEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }
    }
    private fun getToDo(toDoOrder: ToDoOrder){
        getToDoJob?.cancel()
        getToDoJob = toDoUseCases.getToDo(toDoOrder)
            .onEach { todo ->
                _state.value = state.value.copy(
                    todo = todo,
                    toDoOrder = toDoOrder

                )
            }
            .launchIn(viewModelScope)
    }
}
