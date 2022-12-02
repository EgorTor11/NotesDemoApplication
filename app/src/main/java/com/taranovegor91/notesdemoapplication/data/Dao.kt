package com.taranovegor91.notesdemoapplication.data

import androidx.room.*
import androidx.room.Dao
import com.taranovegor91.notesdemoapplication.domain.models.Note


import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(item: Note)
    @Query("SELECT * FROM notes")
    fun selectAllItems():Flow<MutableList<Note>>
    @Query("SELECT * FROM notes WHERE id= :id LIMIT 1")
    fun getItemById(id:Int): Note
    @Update
    fun updateItem(note: Note)
    @Delete
    fun deleteItem(note: Note)
}