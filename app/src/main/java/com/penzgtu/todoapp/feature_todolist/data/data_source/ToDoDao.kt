package com.penzgtu.todoapp.feature_todolist.data.data_source

import androidx.room.*
import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todolist")
    fun getToDo(): Flow<List<ToDoList>>


    @Query("SELECT * FROM todolist WHERE id = :id")
    suspend fun getToDoById(id: Int): ToDoList?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDoList: ToDoList)

    @Delete
    suspend fun deleteToDo(toDoList: ToDoList)
}