package com.taranovegor91.notesdemoapplication.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taranovegor91.notesdemoapplication.Note
import com.taranovegor91.notesdemoapplication.R
import com.taranovegor91.notesdemoapplication.databinding.ItemNoteBinding


class NoteAdapter(
    private val listener: Listener,
) : ListAdapter<Note, NoteAdapter.NoteHolder>(ItemCallback), View.OnClickListener {

    override fun onClick(v: View) {
        val note = v.tag as Note
        Log.d("On", v.id.toString())
        when (v.id) {
            R.id.tvNote -> listener.onTextClick(note.copy())
            R.id.imDeleteNote -> listener.onDeleteClick(note.copy())
            R.id.imShare -> listener.onShareClick(note.copy())
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        binding.tvNote.setOnClickListener(this)
        binding.imDeleteNote.setOnClickListener(this)
        binding.imShare.setOnClickListener(this)
        binding.checkDone.setOnClickListener{
            val note=binding.checkDone.tag as Note
            note.isDone=binding.checkDone.isChecked
            listener.onCheckBoxClick(note)
        }
        return NoteHolder(binding)
    }

    override fun onViewRecycled(holder: NoteHolder) {
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        Log.d("zzz", "onBindViewHolder")
        val note = getItem(position)
        with(holder.binding) {
            tvNote.tag = note
            imDeleteNote.tag = note
            imShare.tag = note
            checkDone.tag=note
            tvNote.text = note.message
            checkDone.isChecked=note.isDone
        }
    }

    interface Listener {
        fun onTextClick(note: Note)
        fun onDeleteClick(note: Note)
        fun onCheckBoxClick(note: Note)
        fun onShareClick(note: Note)
    }

    class NoteHolder(
        val binding: ItemNoteBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

}
