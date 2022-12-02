package com.taranovegor91.notesdemoapplication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.javafaker.Faker


import com.taranovegor91.mypresentationapp.utils.BackPressedForFragments
import java.util.*


class DetailseNoteFragment : Fragment(R.layout.fragment_note_detailse) ,BackPressedForFragments{
    var countNumber = 0
    var mutableListTextStack = mutableListOf<String>()
    var countStackCurent = 0
    var countForReklama = 4

    companion object {
        val KEY = "note"
    }

    lateinit var binding: FragmentNoteDetailseBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteDetailseBinding.bind(view)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    buildAlertDialog()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        val note: Note = requireArguments().get(KEY) as Note
        binding.edMessage.setText(note.message)
        val faker = Faker()
        visibleImBack()
        visibleImForvard()
        binding.imAddRick.setOnClickListener {
            if (mutableListTextStack.isEmpty()) {
                mutableListTextStack.add(binding.edMessage.text.toString())
            } else {
                mutableListTextStack.removeAt(countStackCurent)
                mutableListTextStack.add((countStackCurent), binding.edMessage.text.toString())
            }

            binding.edMessage.setText(faker.rickAndMorty().quote())
            mutableListTextStack.add(binding.edMessage.text.toString())
            if (mutableListTextStack.isNotEmpty()) {
                countStackCurent = mutableListTextStack.count() - 1 // текущий индекс показываемого
            }
            Log.d("countStackCurent", countStackCurent.toString())
            visibleImBack()
            visibleImForvard()
            countForReklama++
            if (countForReklama % 4 == 0) {
                //    showInterAd()
                Log.d("reklama", countForReklama.toString())
            }
        }
        binding.imBack.setOnClickListener {
            if (countStackCurent >= 1) {
                mutableListTextStack.removeAt(countStackCurent)
                mutableListTextStack.add((countStackCurent), binding.edMessage.text.toString())
                binding.edMessage.setText(mutableListTextStack.get(countStackCurent - 1))
                countStackCurent--
            }
            visibleImBack()
            visibleImForvard()
        }
        binding.imForward.setOnClickListener {
            if (countStackCurent >= 0 && countStackCurent < mutableListTextStack.count() - 1) {
                mutableListTextStack.removeAt(countStackCurent)
                mutableListTextStack.add((countStackCurent), binding.edMessage.text.toString())
                binding.edMessage.setText(mutableListTextStack.get(countStackCurent + 1))
                countStackCurent++
            }
            visibleImBack()
            visibleImForvard()
        }
        binding.btnOk.setOnClickListener {
            note.message = binding.edMessage.text.toString()
            Thread { mainDb.getDao().updateItem(note) }.start()
            val imm = requireActivity().applicationContext.getSystemService(
                AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.edMessage.getWindowToken(), 0)
            findNavController().popBackStack()
        }
        binding.imMikrik.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            startActivityForResult(intent, 10)
        }
        binding.imLastik.setOnClickListener {
            if (binding.edMessage.text.toString() != "") {
                if (mutableListTextStack.isEmpty()) {
                    mutableListTextStack.add(binding.edMessage.text.toString())
                } else {
                    mutableListTextStack.removeAt(countStackCurent)
                    mutableListTextStack.add((countStackCurent), binding.edMessage.text.toString())
                }

                binding.edMessage.setText("")
                mutableListTextStack.add(binding.edMessage.text.toString())
                if (mutableListTextStack.isNotEmpty()) {
                    countStackCurent =
                        mutableListTextStack.count() - 1 // текущий индекс показываемого
                }
                Log.d("countStackCurent", countStackCurent.toString())
                visibleImBack()
                visibleImForvard()
            }
            countNumber = 0
        }
        binding.btnCancel.setOnClickListener {
            buildAlertDialog()
        }
        binding.imAddGalocka.setOnClickListener {
            binding.edMessage.text.insert(binding.edMessage.selectionStart,
                getEmojiByUnicode(Integer.parseInt("2705", 16))) // галочка в юникоде
        }
        binding.imAddNumder.setOnClickListener {
            addNumber()
        }
        binding.imAddNumMikrik.setOnClickListener {
            addNumber()
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            startActivityForResult(intent, 11)
        }
        binding.imShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.edMessage.text.toString())
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        binding.imAddJoke.setOnClickListener {
            if (mutableListTextStack.isEmpty()) {
                mutableListTextStack.add(binding.edMessage.text.toString())
            } else {
                mutableListTextStack.removeAt(countStackCurent)
                mutableListTextStack.add((countStackCurent), binding.edMessage.text.toString())
            }

            binding.edMessage.setText(faker.chuckNorris().fact())
            mutableListTextStack.add(binding.edMessage.text.toString())
            if (mutableListTextStack.isNotEmpty()) {
                countStackCurent = mutableListTextStack.count() - 1 // текущий индекс показываемого
            }
            Log.d("countStackCurent", countStackCurent.toString())
            visibleImBack()
            visibleImForvard()
            countForReklama++
            if (countForReklama % 4 == 0) {
                //  showInterAd()
                Log.d("reklama", countForReklama.toString())
            }
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

    fun getEmojiByUnicode(unicode: Int): String = String(Character.toChars(unicode))
    fun addNumber() {
        if (binding.edMessage.text.toString() == "") {
            countNumber = 0
            countNumber++
            binding.edMessage.append("$countNumber.")
        } else {
            countNumber++
            binding.edMessage.append("\n$countNumber.")
        }

    }

    fun visibleImBack() {
        if (countStackCurent >= 1) {
            binding.imBack.visibility = View.VISIBLE
        } else {
            binding.imBack.visibility = View.INVISIBLE
        }
    }
    fun visibleImForvard() {
        if (countStackCurent >= 0 && countStackCurent < mutableListTextStack.count() - 1) {
            binding.imForward.visibility = View.VISIBLE
        } else {
            binding.imForward.visibility = View.INVISIBLE
        }
    }
    fun buildAlertDialog() {
        val alertDialog: AlertDialog = AlertDialog.Builder(requireContext()).create()

        // Указываем Title
        alertDialog.setTitle("getString(R.string.cancel_editing)")

        // Указываем текст сообщение
        alertDialog.setMessage("getString(R.string.cancel_editing_note_question)")

        // задаем иконку
        alertDialog.setIcon(com.taranovegor91.mypresentationapp.R.drawable.krasn_krest)

        // Обработчик на нажатие OK
        alertDialog.setButton("getString(R.string.Ok)") { dialog, which -> // Код который выполнится после закрытия окна
            findNavController().popBackStack()
        }
        alertDialog.setButton2("getString(R.string.No)") { dialog, which -> // Код который выполнится после закрытия окна

        }
        // показываем Alert
        alertDialog.show()
    }

    override fun onBackPressed() {
        buildAlertDialog()
    }
}
