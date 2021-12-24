package com.penzgtu.todoapp.feature_todolist.domain.util

sealed class OrderType{

    object Ascending: OrderType()
    object Descending: OrderType()
}
