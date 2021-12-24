package com.penzgtu.todoapp.feature_todolist.data.repository

import com.penzgtu.todoapp.feature_todolist.data.data_source.ToDoDao
import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.repository.ToDoListRepository
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(
    private val dao: ToDoDao
) : ToDoListRepository {

    override fun getToDo(): Flow<List<ToDoList>> {
        return dao.getToDo()
    }

    override suspend fun getToDoById(id: Int): ToDoList? {
        return dao.getToDoById(id)
    }

    override suspend fun insertToDo(todolist: ToDoList) {
        return dao.insertToDo(todolist)
    }

    override suspend fun deleteToDo(todolist: ToDoList) {
        return dao.deleteToDo(todolist)
    }
}