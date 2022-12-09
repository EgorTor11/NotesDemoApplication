package com.taranovegor91.notesdemoapplication.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.javafaker.Faker
import com.taranovegor91.notesdemoapplication.domain.models.Note
import com.taranovegor91.notesdemoapplication.domain.useCases.DeleteNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.InsertNoteUseCase
import com.taranovegor91.notesdemoapplication.domain.useCases.UpdateNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RootViewModel(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
) : ViewModel() {

    fun insertNote() {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.execute(Note(null,
                Faker.instance().rickAndMorty().quote(),
                false))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.execute(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.execute(note)
        }
    }
}
