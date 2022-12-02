package com.taranovegor91.notesdemoapplication.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.github.javafaker.Faker
import com.taranovegor91.notesdemoapplication.domain.models.Note
import com.taranovegor91.notesdemoapplication.domain.useCases.DeleteNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.InsertNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.SendNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.UpdateNoteUseCase


class NoteViewModel(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val sendNoteUseCase: SendNoteUseCase,
) : ViewModel() {
    fun insertNote() {
        insertNoteUseCase.execute(Note(null, Faker.instance().rickAndMorty().quote(), false))
    }

    fun deleteNote(note: Note) {
        deleteNoteUseCase.execute(note)
    }

    fun updateNote(note: Note) {
        updateNoteUseCase.execute(note)
    }

    fun sendNote(note: Note) {
        sendNoteUseCase.execute(note)
    }
}
