package com.nigdroid.note_taking_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.nigdroid.note_taking_app.Repository.NoteRepository
import com.nigdroid.note_taking_app.database.NoteDatabase
import com.nigdroid.note_taking_app.databinding.ActivityMainBinding
import com.nigdroid.note_taking_app.viewModel.NoteViewModel
import com.nigdroid.note_taking_app.viewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

     lateinit var noteViewModel: NoteViewModel

     lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()

    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}