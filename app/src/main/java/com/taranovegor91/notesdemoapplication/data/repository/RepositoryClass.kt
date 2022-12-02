package com.taranovegor91.notesdemoapplication.data.repository

import com.taranovegor91.notesdemoapplication.app.mainDb
import com.taranovegor91.notesdemoapplication.domain.interfaces.repository.Repository
import com.taranovegor91.notesdemoapplication.domain.models.Note


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