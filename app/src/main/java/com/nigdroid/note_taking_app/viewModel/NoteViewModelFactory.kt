package com.nigdroid.note_taking_app.viewModel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.nigdroid.note_taking_app.Repository.NoteRepository

class NoteViewModelFactory
    (
    val app: Application,
private val noteRepository: NoteRepository
    ): ViewModelProvider.Factory
{

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {

        return NoteViewModel(app, noteRepository) as T
    }

}