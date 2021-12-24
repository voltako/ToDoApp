package com.penzgtu.todoapp.feature_todolist.todo.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penzgtu.todoapp.feature_todolist.domain.util.OrderType
import com.penzgtu.todoapp.feature_todolist.domain.util.ToDoOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    toDoOrder: ToDoOrder = ToDoOrder.Date(OrderType.Descending),
    onOrderChange: (ToDoOrder) -> Unit
){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = toDoOrder is ToDoOrder.Title,
                onSelect = { onOrderChange(ToDoOrder.Title(toDoOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = toDoOrder is ToDoOrder.Date,
                onSelect = { onOrderChange(ToDoOrder.Date(toDoOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = toDoOrder is ToDoOrder.Color,
                onSelect = { onOrderChange(ToDoOrder.Color(toDoOrder.orderType)) }

            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier.fillMaxWidth()
                ){
            DefaultRadioButton(
                text = "Ascending",
                selected = toDoOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(toDoOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = toDoOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(toDoOrder.copy(OrderType.Descending))
                }
            )

        }
    }
}