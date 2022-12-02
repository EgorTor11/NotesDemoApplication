package com.taranovegor91.notesdemoapplication.domain.useCases

import com.taranovegor91.notesdemoapplication.domain.interfaces.repository.Repository
import com.taranovegor91.notesdemoapplication.domain.models.Note


class GetNoteUseCase(private val repository: Repository) {
    fun execute(): Note {
        return repository.getNote()
    }
}