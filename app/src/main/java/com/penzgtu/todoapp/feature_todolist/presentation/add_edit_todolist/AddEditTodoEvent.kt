package com.penzgtu.todoapp.feature_todolist.presentation.add_edit_todolist

import androidx.compose.ui.focus.FocusState

sealed class AddEditTodoEvent{

    data class EnteredTitle(val value: String): AddEditTodoEvent()
    data class ChangeTitleFocusTitle(val focusState: FocusState): AddEditTodoEvent()
    data class EnteredContent(val value: String): AddEditTodoEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditTodoEvent()
    data class ChangeColor(val color: Int): AddEditTodoEvent()
    object SaveToDo: AddEditTodoEvent()


}