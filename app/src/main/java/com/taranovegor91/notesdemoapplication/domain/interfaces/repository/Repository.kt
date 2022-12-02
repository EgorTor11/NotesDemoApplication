package com.taranovegor91.notesdemoapplication
import com.taranovegor91.mypresentationapp.domain.models.Note


interface Repository {
    fun insertNote(note: Note)
    fun getNote(): Note
    fun deleteNote(note: Note)
    fun apdateNote(note: Note)
}