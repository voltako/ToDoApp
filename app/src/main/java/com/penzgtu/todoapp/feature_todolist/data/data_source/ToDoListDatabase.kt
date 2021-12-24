package com.penzgtu.todoapp.feature_todolist.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList

@Database(
    entities = [ToDoList::class],
    version = 1
)

abstract class ToDoListDatabase: RoomDatabase() {

    abstract val todolistDao: ToDoDao

    companion object{
        const val DATABASE_NAME = "todo_db"
    }
}