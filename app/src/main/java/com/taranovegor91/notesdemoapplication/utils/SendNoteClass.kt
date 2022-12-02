package com.taranovegor91.notesdemoapplication.utils

import android.content.Context
import android.content.Intent
import com.taranovegor91.notesdemoapplication.SendNote
import com.taranovegor91.notesdemoapplication.domain.models.Note


class SendNoteClass(private val context: Context): SendNote {
    override fun sendNote(note: Note) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                note.message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
     //  Toast.makeText(context,"hareIntent",Toast.LENGTH_LONG).show()
        context.startActivity(shareIntent)
    }
}