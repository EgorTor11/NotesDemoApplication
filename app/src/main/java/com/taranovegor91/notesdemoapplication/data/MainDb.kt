package com.taranovegor91.notesdemoapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.taranovegor91.notesdemoapplication.domain.models.Note


@Database(entities = [Note::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract  fun getDao(): Dao
    companion object {
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "test.db"
            ).build()
        }
    }
}