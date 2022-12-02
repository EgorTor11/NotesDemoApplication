package com.taranovegor91.notesdemoapplication



class SendNoteUseCase(private val sendNote: SendNote) {
    fun execute(note: Note) {
        sendNote.sendNote(note)
    }
}