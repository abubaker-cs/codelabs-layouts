package org.abubaker.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import org.abubaker.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Global Variables
    private lateinit var binding: ActivityMainBinding

    // Data Library
    private val myName : MyName = MyName("Alex Haecky")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Creating a binding class to get references for all UI Elements
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Assigning our custom value to the the DATA-VARIABLE in activity_main.xml in <data><variable /></data>
        binding.myName = myName

        // When the DONE button will be pressed
        binding.doneButton.setOnClickListener{
            addNickName(it) // it = done_button | view passed as the argument
        }

        // When the TextField will be clicked
        binding.nicknameText.setOnClickListener {
            updateNickName(it)
        }
    }

    // Function: Add Nickname
    private fun addNickName(view: View) {

        // Kotlinize the function by using apply{}.
        binding.apply {

            // Note: When using data binding, it is necessary to explicitly convert the Editable to a String.
            // binding.nicknameText.text = binding.nicknameEdit.text.toString()
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll() // This will refresh UI with the updated values in the binding object

            // Hide Edit field and Show new Nickname
            binding.nicknameEdit.visibility = View.GONE
            binding.doneButton.visibility = View.GONE // Replacement for view.visibility = View.GONE

            binding.nicknameText.visibility = View.VISIBLE
        }

        // Hide the Keyboard when the user will press DONE
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Function: Update Nickname
    private fun updateNickName (view: View){
        // Show: Edit field and Done buttons
        binding.nicknameEdit.visibility = View.VISIBLE

        // Hide the Done button
        binding.doneButton.visibility = View.VISIBLE

        // set keyboard's focus
        binding.nicknameEdit.requestFocus()

        // show keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit, 0)
    }

}