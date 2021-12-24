package com.penzgtu.todoapp.di

import android.app.Application
import androidx.room.Room
import com.penzgtu.todoapp.feature_todolist.data.data_source.ToDoListDatabase
import com.penzgtu.todoapp.feature_todolist.data.repository.ToDoRepositoryImpl
import com.penzgtu.todoapp.feature_todolist.domain.repository.ToDoListRepository
import com.penzgtu.todoapp.feature_todolist.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideToDoListDatabase(app: Application): ToDoListDatabase{
        return Room.databaseBuilder(
            app,
            ToDoListDatabase::class.java,
            ToDoListDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoListRepository(db: ToDoListDatabase): ToDoListRepository{
        return ToDoRepositoryImpl(db.todolistDao)
    }

    @Provides
    @Singleton
    fun provideToDoUseCases(repository: ToDoListRepository): ToDoUseCases{
        return ToDoUseCases(
            getToDo = GetToDo(repository),
            deleteToDo = DeleteToDo(repository),
            addToDo = AddToDo(repository),
            getToDoUseCase = GetToDoUseCase(repository)
        )
    }
}