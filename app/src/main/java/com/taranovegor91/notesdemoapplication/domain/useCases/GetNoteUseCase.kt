package com.taranovegor91.notesdemoapplication



class GetNoteUseCase(private val repository: Repository) {
    fun execute(): Note {
        return repository.getNote()
    }
}