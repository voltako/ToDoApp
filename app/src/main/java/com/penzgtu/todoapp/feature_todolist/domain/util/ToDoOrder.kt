package com.penzgtu.todoapp.feature_todolist.domain.util

sealed class ToDoOrder(val orderType: OrderType){
    class Title(orderType: OrderType): ToDoOrder(orderType)
    class Date(orderType: OrderType): ToDoOrder(orderType)
    class Color(orderType: OrderType): ToDoOrder(orderType)

    fun copy(orderType: OrderType): ToDoOrder{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
