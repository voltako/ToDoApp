package com.penzgtu.todoapp.feature_todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.penzgtu.todoapp.ui.theme.*
import java.lang.Exception


@Entity
data class ToDoList(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null

){
    companion object{
        val todoColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidToDOException(message: String): Exception(message)