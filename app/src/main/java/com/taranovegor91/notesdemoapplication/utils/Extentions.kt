package com.taranovegor91.notesdemoapplication.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.taranovegor91.notesdemoapplication.R
import java.util.*

fun Context.showToastLong(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()
fun Context.buildAlertDialog(navController: NavController) {
    val alertDialog: AlertDialog = AlertDialog.Builder(this).create()

    // Указываем Title
    alertDialog.setTitle(getString(R.string.undo_note_editing))

    // Указываем текст сообщение
    alertDialog.setMessage(getString(R.string.want_to_undo_editing_note))

    // задаем иконку
    alertDialog.setIcon(androidx.appcompat.R.drawable.abc_ic_clear_material)

    // Обработчик на нажатие OK
    alertDialog.setButton(getString(R.string.ok)) { dialog, which -> // Код который выполнится после закрытия окна
        navController.popBackStack()
    }
    alertDialog.setButton2(getString(R.string.no)) { dialog, which -> // Код который выполнится после закрытия окна

    }
    // показываем Alert
    alertDialog.show()
}
fun Context.shareText(text: String){
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.getVoiceInputIntent():Intent{
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
return intent
}