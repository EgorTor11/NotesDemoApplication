package com.taranovegor91.notesdemoapplication

import com.taranovegor91.mypresentationapp.domain.models.Note
import com.taranovegor91.mypresentationapp.domain.interfaces.repository.Repository

class RepositoryClass : Repository {
    override fun insertNote(note: Note) {
        Thread { mainDb.getDao().insertItem(note) }.start()
    }

    override fun getNote(): Note {
        return Note(null, "Hello world",true) // заглушка
    }

    override fun deleteNote(note: Note) {
        Thread { mainDb.getDao().deleteItem(note) }.start()
    }

    override fun apdateNote(note: Note) {
        Thread { mainDb.getDao().updateItem(note) }.start()
    }
}