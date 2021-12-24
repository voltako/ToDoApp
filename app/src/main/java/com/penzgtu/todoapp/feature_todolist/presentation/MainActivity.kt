package com.penzgtu.todoapp.feature_todolist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.penzgtu.todoapp.feature_todolist.presentation.add_edit_todolist.AddEditToDoScreen
import com.penzgtu.todoapp.feature_todolist.presentation.util.Screen
import com.penzgtu.todoapp.feature_todolist.todo.ToDoScreen
import com.penzgtu.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ToDoScreen.route
                    ){
                        composable(
                            route = Screen.ToDoScreen.route){
                                ToDoScreen(navController = navController)
                            }
                        composable(
                            route = Screen.AddEditToDoScreen.route +
                                    "?todoId={todoId}&todoColor={todoColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "todoId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "todoColor"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                            ){
                            val color = it.arguments?.getInt("todoColor") ?: -1
                            AddEditToDoScreen(
                                navController = navController,
                                todoColor = color
                            )
                        }

                    }

                }
            }
        }
    }
}
