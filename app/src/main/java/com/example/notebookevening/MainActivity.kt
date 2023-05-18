package com.example.notebookevening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var submitBtn:Button
    lateinit var notesListView:ListView
    var notesArray= arrayListOf<String>("Note one","Note two")
    lateinit var addResultLauncher:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitBtn=findViewById(R.id.submitNote)
        notesListView=findViewById(R.id.notesListView)

        var note=intent.getStringExtra("note")
        println(note)
        if (note != null) {
            notesArray.add(note)
        }

        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,notesArray)
        notesListView.adapter=adapter
        submitBtn.setOnClickListener {
            var intent:Intent=Intent(applicationContext,AddNoteActivity::class.java)
            startActivity(intent)

        }
        notesListView.setOnItemClickListener { adapterView, view, position, id ->
            var intent=Intent(this,AddNoteActivity::class.java)
            intent.putExtra("noteId",notesArray[position])
            startActivity(intent)

        }
        registerActivityResultLauncher()

    }
    fun registerActivityResultLauncher(){
        addResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                resultAddNote->
                val resultCode=resultAddNote.resultCode
                val data=resultAddNote.data
                if(resultCode== RESULT_OK && data!=null){
                    val resNote:String=data.getStringExtra("note").toString()
                    if(resNote!=null){
                        notesArray.add(resNote)
                        println(notesArray)
                        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,notesArray)
                        notesListView.adapter=adapter
                    }
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater=menuInflater
        menuInflater.inflate(R.menu.add_note_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         super.onOptionsItemSelected(item)
        if(item.itemId==R.id.addNote){
            val intent:Intent=Intent(applicationContext,AddNoteActivity::class.java)
            addResultLauncher.launch(intent)
//            startActivity(intent)
            return true
        }else if(item.itemId==R.id.deleteNotes){
            notesArray.clear()
//            val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,newArray)
//            notesListView.adapter=adapter
            Toast.makeText(this,"Delete all notes",Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
}