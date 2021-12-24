package com.penzgtu.todoapp.feature_todolist.domain.repository

import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import kotlinx.coroutines.flow.Flow

interface ToDoListRepository {

    fun getToDo(): Flow<List<ToDoList>>

    suspend fun getToDoById(id: Int): ToDoList?

    suspend fun insertToDo(todolist: ToDoList)

    suspend fun deleteToDo(todolist: ToDoList)
}