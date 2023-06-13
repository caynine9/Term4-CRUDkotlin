package umbjm.ft.inf.crudroomapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import umbjm.ft.inf.crudroomapp.databinding.ActivityEditBinding
import umbjm.ft.inf.crudroomapp.room.Constant
import umbjm.ft.inf.crudroomapp.room.Note
import umbjm.ft.inf.crudroomapp.room.NoteDB

class EditActivity : AppCompatActivity() {

    val db by lazy { NoteDB(this) }
    private var noteId: Int = 0

    private lateinit var binding : ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupListener()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType){
            Constant.TYPE_CREATE -> {
                binding.buttonUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                getNote()
            }
            Constant.TYPE_UPDATE -> {
                binding.buttonSave.visibility = View.GONE
                getNote()
            }
        }
    }

    private fun setupListener() {
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(0, binding.editNim.text.toString(), binding.editNama.text.toString(),binding.editAlamat.text.toString(),binding.editHp.text.toString())
                )
                finish()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updateNote(
                    Note(noteId, binding.editNim.text.toString(), binding.editNama.text.toString(),binding.editAlamat.text.toString(),binding.editHp.text.toString())
                )
                finish()
            }
        }
    }

    fun getNote(){
        noteId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNote(noteId)[0]
            binding.editNim.setText(notes.NIM)
            binding.editNama.setText(notes.Nama)
            binding.editAlamat.setText(notes.Alamat_lengkap)
            binding.editHp.setText(notes.No_hp)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}