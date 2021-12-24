package com.penzgtu.todoapp.feature_todolist.todo

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.penzgtu.todoapp.feature_todolist.presentation.util.Screen
import com.penzgtu.todoapp.feature_todolist.todo.components.OrderSection
import com.penzgtu.todoapp.feature_todolist.todo.components.ToDoItem
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun ToDoScreen(
    navController: NavController,
    viewModel: ToDoViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate(Screen.AddEditToDoScreen.route)
            },
            backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Добавить дело")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Список дел",
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = {
                                     viewModel.onEvent(ToDoEvent.ToggleOrderSection)

                },
                ) {
                   Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort"
                   )
                }

            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
                ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    toDoOrder = state.toDoOrder,
                    onOrderChange = {
                        viewModel.onEvent(ToDoEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.todo){ todo ->
                    ToDoItem(
                        todo = todo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                       navController.navigate(
                                           Screen.AddEditToDoScreen.route +
                                                   "?todoId=${todo.id}&todoColor=${todo.color}"
                                       )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(ToDoEvent.DeleteToDo(todo))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Дело удалено",
                                    actionLabel = "Отмена"
                                )
                                if (result==SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(ToDoEvent.RestoreToDo)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
    }

}