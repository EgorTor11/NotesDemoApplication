package com.taranovegor91.notesdemoapplication


class DeleteNoteUseCase(private val repository: Repository) {
    fun execute(note: Note){
        repository.deleteNote(note)
    }
}