package com.penzgtu.todoapp.feature_todolist.domain.use_case

data class ToDoUseCases(
    val getToDo: GetToDo,
    val deleteToDo: DeleteToDo,
    val addToDo: AddToDo,
    val getToDoUseCase: GetToDoUseCase
)
