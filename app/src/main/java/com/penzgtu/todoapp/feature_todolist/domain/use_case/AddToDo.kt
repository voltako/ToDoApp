package com.penzgtu.todoapp.feature_todolist.domain.use_case

import com.penzgtu.todoapp.feature_todolist.domain.model.InvalidToDOException
import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.repository.ToDoListRepository
import kotlin.jvm.Throws

class AddToDo(
    private val repository: ToDoListRepository
) {

    @Throws(InvalidToDOException::class)
    suspend operator fun invoke(toDoList: ToDoList){
        if(toDoList.title.isBlank()){
            throw InvalidToDOException("Заголовок не может быть пустой.")
        }
        if (toDoList.content.isBlank()){
            throw InvalidToDOException("Содержание не может быть пустое.")
        }
        repository.insertToDo(toDoList)
    }
}