package com.penzgtu.todoapp.feature_todolist.domain.use_case

import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.repository.ToDoListRepository

class GetToDoUseCase(
    private val repository: ToDoListRepository
) {

    suspend operator fun invoke(id: Int): ToDoList? {
        return repository.getToDoById(id)
    }
}