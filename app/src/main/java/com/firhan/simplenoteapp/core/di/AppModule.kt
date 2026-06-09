package com.firhan.simplenoteapp.core.di

import android.app.Application
import androidx.room.Room
import com.firhan.simplenoteapp.data.local.NoteDatabase
import com.firhan.simplenoteapp.data.repository.NoteRepository
import com.firhan.simplenoteapp.data.repository.NoteRepositoryImpl
import com.firhan.simplenoteapp.domain.usecase.AddNoteUseCase
import com.firhan.simplenoteapp.domain.usecase.DeleteNoteUseCase
import com.firhan.simplenoteapp.domain.usecase.GetNoteUseCase
import com.firhan.simplenoteapp.domain.usecase.GetNotesUseCase
import com.firhan.simplenoteapp.domain.usecase.NoteUseCases
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
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }
}