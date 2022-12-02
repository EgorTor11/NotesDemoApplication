package com.taranovegor91.notesdemoapplication

import androidx.lifecycle.ViewModel
import com.github.javafaker.Faker


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
