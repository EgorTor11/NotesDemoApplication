package com.taranovegor91.notesdemoapplication.domain.interfaces.repository


import com.taranovegor91.notesdemoapplication.domain.models.Note


interface Repository {
    fun insertNote(note: Note)
    fun getNote(): Note
    fun deleteNote(note: Note)
    fun apdateNote(note: Note)
}