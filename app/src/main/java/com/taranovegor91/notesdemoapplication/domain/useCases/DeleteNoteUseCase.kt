package com.taranovegor91.notesdemoapplication.domain.useCases


import com.taranovegor91.notesdemoapplication.domain.interfaces.repository.Repository
import com.taranovegor91.notesdemoapplication.domain.models.Note


class DeleteNoteUseCase(private val repository: Repository) {
    fun execute(note: Note){
        repository.deleteNote(note)
    }
}