package com.taranovegor91.notesdemoapplication



class InsertNoteUseCase(private val repository: Repository) {
    fun execute(note: Note){
        repository.insertNote(note)
    }
}