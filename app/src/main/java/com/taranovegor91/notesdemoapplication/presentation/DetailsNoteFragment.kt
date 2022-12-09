package com.taranovegor91.notesdemoapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.taranovegor91.notesdemoapplication.R
import com.taranovegor91.notesdemoapplication.databinding.FragmentNoteDetailsBinding
import com.taranovegor91.notesdemoapplication.domain.models.Note
import com.taranovegor91.notesdemoapplication.presentation.viewModels.DetailseNoteViewModel
import com.taranovegor91.notesdemoapplication.utils.buildAlertDialog
import com.taranovegor91.notesdemoapplication.utils.getVoiceInputIntent
import com.taranovegor91.notesdemoapplication.utils.shareText

class DetailsNoteFragment : Fragment(R.layout.fragment_note_details) {
    val vm: DetailseNoteViewModel by viewModels()

    companion object {
        val KEY = "note"
    }

    lateinit var binding: FragmentNoteDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteDetailsBinding.bind(view)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    requireContext().buildAlertDialog(findNavController())
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        val note: Note = requireArguments().get(KEY) as Note
        binding.edMessage.setText(note.message)
        vm.edMessageAppendLiveData.observe(viewLifecycleOwner) {
            binding.edMessage.append(it)
        }
        vm.edMessageLiveData.observe(viewLifecycleOwner) {
            binding.edMessage.setText(it)
        }
        vm.visibleBackLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.imBack.visibility = View.VISIBLE
            } else {
                binding.imBack.visibility = View.INVISIBLE
            }
        }
        vm.visibleForvardLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.imForward.visibility = View.VISIBLE
            } else {
                binding.imForward.visibility = View.INVISIBLE
            }
        }
        binding.imAddJoke.setOnClickListener {
            vm.addJoke(binding.edMessage.text.toString())
        }
        binding.imAddRick.setOnClickListener {
            vm.addRick(binding.edMessage.text.toString())
        }
        binding.imBack.setOnClickListener {
            vm.back(binding.edMessage.text.toString())
        }
        binding.imForward.setOnClickListener {
            vm.forvard(binding.edMessage.text.toString())
        }
        binding.imLastik.setOnClickListener {
            vm.lastik(binding.edMessage.text.toString())
        }
        binding.imMikrik.setOnClickListener {
            startActivityForResult(requireContext().getVoiceInputIntent(), 11)
        }
        binding.imAddNumMikrik.setOnClickListener {
            vm.addNumber(binding.edMessage.text.toString())
            startActivityForResult(requireContext().getVoiceInputIntent(), 11)
        }
        binding.imShare.setOnClickListener {
            requireContext().shareText(binding.edMessage.text.toString())
        }
        binding.imAddGalocka.setOnClickListener {
            binding.edMessage.text.insert(binding.edMessage.selectionStart,
                getEmojiByUnicode(Integer.parseInt("2705", 16))) // галочка в юникоде
        }
        binding.imAddNumder.setOnClickListener {
            vm.addNumber(binding.edMessage.text.toString())
        }
        binding.btnCancel.setOnClickListener {
            requireContext().buildAlertDialog(findNavController())
            closeKeyboard()
        }
        binding.btnOk.setOnClickListener {
            vm.updateNoteInDB(note, binding.edMessage.text.toString())
            findNavController().popBackStack()
            closeKeyboard()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
            if (requestCode == 11) {
                binding.edMessage.append((" " + data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    ?.get(0)) ?: "")
            }
            if (requestCode == 10) {
                binding.edMessage.text.insert(binding.edMessage.selectionStart,
                    (" " + data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        ?.get(0)) ?: "")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        vm.edMessageLiveData.value = binding.edMessage.text.toString()
    }

    fun getEmojiByUnicode(unicode: Int): String = String(Character.toChars(unicode))
    fun closeKeyboard() {
        val imm =
            requireActivity().applicationContext.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edMessage.getWindowToken(), 0)
    }
}
