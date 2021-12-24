package com.penzgtu.todoapp.feature_todolist.todo

import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.util.OrderType
import com.penzgtu.todoapp.feature_todolist.domain.util.ToDoOrder

data class ToDoState(
    val todo: List<ToDoList> = emptyList(),
    val toDoOrder: ToDoOrder = ToDoOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
