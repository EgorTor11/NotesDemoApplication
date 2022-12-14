package com.taranovegor91.notesdemoapplication.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.taranovegor91.notesdemoapplication.*
import com.taranovegor91.notesdemoapplication.app.mainDb
import com.taranovegor91.notesdemoapplication.databinding.FragmentRootBinding
import com.taranovegor91.notesdemoapplication.domain.models.Note
import com.taranovegor91.notesdemoapplication.presentation.viewModels.RootViewModel
import com.taranovegor91.notesdemoapplication.presentation.viewModels.RootViewModelFactory
import com.taranovegor91.notesdemoapplication.utils.shareText


class RootFragment:Fragment(R.layout.fragment_root) {
    private lateinit var binding: FragmentRootBinding
    lateinit var vm: RootViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
        vm = ViewModelProvider(this, RootViewModelFactory(requireActivity())).get(
            RootViewModel::class.java)
        val adapter = NoteAdapter(object : NoteAdapter.Listener {
            override fun onTextClick(note: Note) {
                findNavController().navigate(R.id.action_rootFragment_to_detailseNoteFragment,
                    bundleOf(DetailsNoteFragment.KEY to note))
            }
            override fun onDeleteClick(note: Note) {
                vm.deleteNote(note)
            }
            override fun onCheckBoxClick(note: Note) {
                vm.updateNote(note)
            }

            override fun onShareClick(note: Note) {
              //  vm.sendNote(note)
                requireContext().shareText(note.message)
            }
        })
        (binding.rcNote.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        binding.rcNote.layoutManager = LinearLayoutManager(requireContext())
        binding.rcNote.adapter = adapter
        mainDb.getDao().selectAllItems().asLiveData().observe(requireActivity()) { list ->
            adapter.submitList(ArrayList(list))
        }

        binding.btnAddNote.setOnClickListener{
            vm.insertNote()
        }
    }



}
