package com.taranovegor91.notesdemoapplication
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "message")
    var message:String,
    @ColumnInfo(name = "isDone")
    var isDone:Boolean

) : Parcelable