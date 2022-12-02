package com.taranovegor91.notesdemoapplication.presentation.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taranovegor91.notesdemoapplication.data.repository.RepositoryClass
import com.taranovegor91.notesdemoapplication.domain.useCases.DeleteNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.InsertNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.SendNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.UpdateNoteUseCase
import com.taranovegor91.notesdemoapplication.utils.SendNoteClass


class NoteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    var repository = RepositoryClass()
    var sendNote = SendNoteClass(context)
    var insertNoteUseCase = InsertNoteUseCase(repository)
    var deleteNoteUseCase = DeleteNoteUseCase(repository)
    var updateNoteUseCase = UpdateNoteUseCase(repository)
    var sendNoteUseCase = SendNoteUseCase(sendNote)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(insertNoteUseCase, deleteNoteUseCase, updateNoteUseCase,sendNoteUseCase) as T
    }
}