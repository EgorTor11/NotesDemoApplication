package com.taranovegor91.notesdemoapplication.data.repository

import com.taranovegor91.notesdemoapplication.app.mainDb
import com.taranovegor91.notesdemoapplication.domain.interfaces.repository.Repository
import com.taranovegor91.notesdemoapplication.domain.models.Note


class RepositoryClass : Repository {
    override fun insertNote(note: Note) {
        mainDb.getDao().insertItem(note)
    }

    override fun getNote(): Note {
        return Note(null, "Hello world",true) // заглушка
    }

    override fun deleteNote(note: Note) {
         mainDb.getDao().deleteItem(note)
    }

    override fun apdateNote(note: Note) {
         mainDb.getDao().updateItem(note)
    }
}