package com.taranovegor91.notesdemoapplication

import com.taranovegor91.notesdemoapplication.domain.models.Note


interface SendNote {
    fun sendNote(note: Note)
}