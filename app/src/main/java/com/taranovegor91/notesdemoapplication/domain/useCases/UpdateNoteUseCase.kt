package com.taranovegor91.notesdemoapplication

class UpdateNoteUseCase(private val repository: Repository) {
    fun execute(note: Note){
        repository.apdateNote(note)
    }
}