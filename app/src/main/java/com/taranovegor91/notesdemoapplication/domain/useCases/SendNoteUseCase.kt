package com.taranovegor91.notesdemoapplication.domain.useCases

import com.taranovegor91.notesdemoapplication.SendNote
import com.taranovegor91.notesdemoapplication.domain.models.Note


class SendNoteUseCase(private val sendNote: SendNote) {
    fun execute(note: Note) {
        sendNote.sendNote(note)
    }
}