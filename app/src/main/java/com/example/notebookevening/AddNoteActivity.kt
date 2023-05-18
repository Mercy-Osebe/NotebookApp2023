package com.example.notebookevening

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddNoteActivity : AppCompatActivity() {
    lateinit var noteInput:EditText
    lateinit var cancelBtn:Button
    lateinit var addBtn:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        noteInput=findViewById(R.id.noteInput)
        cancelBtn=findViewById(R.id.cancelBtn)
        addBtn=findViewById(R.id.addBtn)

        var mainIntentNote=intent.getStringExtra("noteId")
        noteInput.setText(mainIntentNote)


        cancelBtn.setOnClickListener {

            Toast.makeText(applicationContext,"Add note cancelled",Toast.LENGTH_SHORT).show()

            finish()
        }
        addBtn.setOnClickListener {
            val input:String=noteInput.text.toString()
            var intent:Intent=Intent()
            intent.putExtra("note",input)
            intent.putExtra("noteTwo",input)
            setResult(RESULT_OK,intent)
            finish()

//            println()
        }

    }
}