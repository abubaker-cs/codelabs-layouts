package org.abubaker.aboutme

import android.content.Context
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // When the DONE button will be pressed
        findViewById<Button>(R.id.done_button).setOnClickListener{
            addNickName(it) // it = done_button | view passed as the argument
        }

        // When the TextField will be clicked
        findViewById<TextView>(R.id.nickname_text).setOnClickListener {
            updateNickName(it)
        }
    }

    // Function: Add Nickname
    private fun addNickName(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextVeiw = findViewById<TextView>(R.id.nickname_text)

        nicknameTextVeiw.text = editText.text

        // Hide Edit field and Show new Nickname
        editText.visibility = View.GONE
        nicknameTextVeiw.visibility = View.VISIBLE

        // Hide the Keyboard when the user will press DONE
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Function: Update Nickname
    private fun updateNickName (view: View){
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        // Show: Edit field and Done buttons
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE

        // Hide the Done button
        view.visibility = View.GONE
    }

}