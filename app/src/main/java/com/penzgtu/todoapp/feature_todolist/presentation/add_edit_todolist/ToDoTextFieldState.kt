package com.penzgtu.todoapp.feature_todolist.presentation.add_edit_todolist

data class ToDoTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
