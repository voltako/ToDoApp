package com.penzgtu.todoapp.feature_todolist.presentation.util

sealed class Screen(val route: String){
    object ToDoScreen: Screen("todo_screen")
    object AddEditToDoScreen: Screen("add_edit_todo_screen")
}
