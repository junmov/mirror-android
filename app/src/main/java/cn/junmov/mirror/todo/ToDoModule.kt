package cn.junmov.mirror.todo

import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.todo.domain.ChangeTodoStateUseCase
import cn.junmov.mirror.todo.domain.CreateTodoUseCase
import cn.junmov.mirror.todo.domain.FlowAllTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ToDoModule {

    @Singleton
    @Provides
    fun changeTodoStateUseCase(database: MirrorDatabase) =
        ChangeTodoStateUseCase(database.toDoDao())

    @Singleton
    @Provides
    fun createTodoUseCase(database: MirrorDatabase) =
        CreateTodoUseCase(database.toDoDao())

    @Singleton
    @Provides
    fun flowAllTodoUseCase(database: MirrorDatabase) =
        FlowAllTodoUseCase(database.toDoDao())

}