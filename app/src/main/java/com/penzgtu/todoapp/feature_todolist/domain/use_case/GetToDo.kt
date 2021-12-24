package com.penzgtu.todoapp.feature_todolist.domain.use_case

import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.repository.ToDoListRepository
import com.penzgtu.todoapp.feature_todolist.domain.util.OrderType
import com.penzgtu.todoapp.feature_todolist.domain.util.ToDoOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetToDo(
    private val repository: ToDoListRepository
) {


    operator fun invoke(toDoOrder: ToDoOrder = ToDoOrder.Date(OrderType.Descending)
    ): Flow<List<ToDoList>>{
        return repository.getToDo().map { todo ->
            when(toDoOrder.orderType) {
                is OrderType.Ascending -> {
                    when(toDoOrder){
                        is ToDoOrder.Title -> todo.sortedBy { it.title.lowercase() }
                        is ToDoOrder.Date -> todo.sortedBy { it.timestamp }
                        is ToDoOrder.Color -> todo.sortedBy { it.color }
                    }

                }
                is OrderType.Descending -> {
                    when(toDoOrder){
                        is ToDoOrder.Title -> todo.sortedByDescending { it.title.lowercase() }
                        is ToDoOrder.Date -> todo.sortedByDescending { it.timestamp }
                        is ToDoOrder.Color -> todo.sortedByDescending { it.color }
                    }


                }
            }
        }
    }
}