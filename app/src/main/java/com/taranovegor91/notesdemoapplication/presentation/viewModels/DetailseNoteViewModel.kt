package com.taranovegor91.notesdemoapplication.presentation.viewModels

import android.app.AlertDialog
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.github.javafaker.Faker
import com.taranovegor91.notesdemoapplication.R
import com.taranovegor91.notesdemoapplication.app.mainDb
import com.taranovegor91.notesdemoapplication.domain.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailseNoteViewModel : ViewModel() {
    val edMessageLiveData = MutableLiveData<String>()
    val edMessageAppendLiveData = MutableLiveData<String>()
    val visibleBackLiveData = MutableLiveData<Boolean>()
    val visibleForvardLiveData = MutableLiveData<Boolean>()
    val faker = Faker()
    var countStackCurent = 0
    var countNumber = 0
    var mutableListTextStack = mutableListOf<String>()

    fun addJoke(edTextCurrent: String) {
        stackCheck(edTextCurrent)
        edMessageLiveData.value = faker.chuckNorris().fact()
        mutableListTextStack.add(edMessageLiveData.value.toString())
        if (mutableListTextStack.isNotEmpty()) {
            countStackCurent = mutableListTextStack.count() - 1 // текущий индекс показываемого
        }
        visibleImBack()
        visibleImForvard()
    }
    fun addRick(edTextCurrent: String) {
        stackCheck(edTextCurrent)
        edMessageLiveData.value = faker.rickAndMorty().quote()
        mutableListTextStack.add(edMessageLiveData.value.toString())
        if (mutableListTextStack.isNotEmpty()) {
            countStackCurent = mutableListTextStack.count() - 1 // текущий индекс показываемого
        }
        visibleImBack()
        visibleImForvard()
    }
    fun back(edTextCurrent: String) {
        if (countStackCurent >= 1) {
            mutableListTextStack.removeAt(countStackCurent)
            mutableListTextStack.add((countStackCurent), edTextCurrent)
            edMessageLiveData.value = mutableListTextStack.get(countStackCurent - 1)
            countStackCurent--
        }
        visibleImBack()
        visibleImForvard()
    }
    fun forvard(edTextCurrent: String) {
        if (countStackCurent >= 0 && countStackCurent < mutableListTextStack.count() - 1) {
            mutableListTextStack.removeAt(countStackCurent)
            mutableListTextStack.add((countStackCurent), edTextCurrent)
            edMessageLiveData.value =mutableListTextStack.get(countStackCurent + 1)
            countStackCurent++
        }
        visibleImBack()
        visibleImForvard()
    }
    fun lastik(edTextCurrent: String){
        if (edTextCurrent != "") {
            if (mutableListTextStack.isEmpty()) {
                mutableListTextStack.add(edTextCurrent)
            } else {
                mutableListTextStack.removeAt(countStackCurent)
                mutableListTextStack.add((countStackCurent), edTextCurrent)
            }
            edMessageLiveData.value =""
            mutableListTextStack.add(edMessageLiveData.value.toString())
            if (mutableListTextStack.isNotEmpty()) {
                countStackCurent =
                    mutableListTextStack.count() - 1 // текущий индекс показываемого
            }
            visibleImBack()
            visibleImForvard()
        }
        countNumber=0
    }
    fun updateNoteInDB(note:Note,edTextCurrent: String){
        note.message = edTextCurrent
       viewModelScope.launch(Dispatchers.IO) { mainDb.getDao().updateItem(note)}
    }
    fun addNumber(edTextCurrent: String) {
        if (edTextCurrent == "") {
            countNumber = 0
            countNumber++
            edMessageAppendLiveData.value="$countNumber."
        } else {
            countNumber++
            edMessageAppendLiveData.value="\n$countNumber."
        }

    }
    private fun stackCheck(edTextCurrent: String) {
        if (mutableListTextStack.isEmpty()) {
            mutableListTextStack.add(edTextCurrent)
        } else {
            mutableListTextStack.removeAt(countStackCurent)
            mutableListTextStack.add((countStackCurent), edTextCurrent)
        }
    }
    private fun visibleImBack() {
        visibleBackLiveData.value = countStackCurent >= 1
    }
    private fun visibleImForvard() {
        visibleForvardLiveData.value =
            countStackCurent >= 0 && countStackCurent < mutableListTextStack.count() - 1
    }
}