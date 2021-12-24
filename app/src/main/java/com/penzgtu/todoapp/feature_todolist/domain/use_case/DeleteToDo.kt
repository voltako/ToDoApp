package com.penzgtu.todoapp.feature_todolist.domain.use_case

import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.repository.ToDoListRepository

class DeleteToDo(
    private val repository: ToDoListRepository
) {

    suspend operator fun invoke(toDoList: ToDoList){
        repository.deleteToDo(toDoList)

    }
}