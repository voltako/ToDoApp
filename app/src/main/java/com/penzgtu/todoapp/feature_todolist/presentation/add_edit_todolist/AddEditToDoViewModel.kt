package com.penzgtu.todoapp.feature_todolist.presentation.add_edit_todolist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penzgtu.todoapp.feature_todolist.domain.model.InvalidToDOException
import com.penzgtu.todoapp.feature_todolist.domain.model.ToDoList
import com.penzgtu.todoapp.feature_todolist.domain.use_case.ToDoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditToDoViewModel @Inject constructor(
    private val toDoUseCases: ToDoUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _todoTitle = mutableStateOf(ToDoTextFieldState(
        hint = "Введите название..."
    ))
    val todoTitle: State<ToDoTextFieldState> = _todoTitle

    private val _todoContent = mutableStateOf(ToDoTextFieldState(
        hint = "Введите, что нужно сделать..."
    ))
    val todoContent: State<ToDoTextFieldState> = _todoContent

    private val _todoColor = mutableStateOf(ToDoList.todoColors.random().toArgb())
    val todoColor :State<Int> = _todoColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTodoId: Int? = null

    init {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            if(todoId != -1){
                viewModelScope.launch {
                    toDoUseCases.getToDoUseCase(todoId)?.also { todo ->
                        currentTodoId = todo.id
                        _todoTitle.value = todoTitle.value.copy(
                            text = todo.title,
                            isHintVisible = false
                        )
                        _todoContent.value = todoContent.value.copy(
                            text = todo.content,
                            isHintVisible = false
                        )
                        _todoColor.value = todo.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when(event){
            is AddEditTodoEvent.EnteredTitle -> {
                _todoTitle.value = todoTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTodoEvent.ChangeTitleFocusTitle -> {
                _todoTitle.value = todoTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            todoTitle.value.text.isBlank()
                )
            }
            is AddEditTodoEvent.EnteredContent -> {
                _todoContent.value = _todoContent.value.copy(
                    text = event.value
                )
            }
            is AddEditTodoEvent.ChangeContentFocus -> {
                _todoContent.value = _todoContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            todoContent.value.text.isBlank()
                )
            }
            is AddEditTodoEvent.ChangeColor -> {
                _todoColor.value = event.color
            }
            is AddEditTodoEvent.SaveToDo -> {
                viewModelScope.launch{
                    try{
                        toDoUseCases.addToDo(
                            ToDoList(
                                title = todoTitle.value.text,
                                content = todoContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = todoColor.value,
                                id = currentTodoId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveToDo)

                    } catch (e: InvalidToDOException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Не получилось сохранить"
                            )
                        )

                    }
                }
            }

        }
    }


    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveToDo: UiEvent()
    }
}