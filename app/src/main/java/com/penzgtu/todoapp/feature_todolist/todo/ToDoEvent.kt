package com.penzgtu.todoapp.feature_todolist.todo

import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.util.ToDoOrder

sealed class ToDoEvent {
    data class Order(val toDoOrder: ToDoOrder): ToDoEvent()
    data class DeleteToDo(val toDoList: ToDoList): ToDoEvent()
    object RestoreToDo: ToDoEvent()
    object ToggleOrderSection: ToDoEvent()
}