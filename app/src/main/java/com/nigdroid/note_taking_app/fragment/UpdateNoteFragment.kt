package com.nigdroid.note_taking_app.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.nigdroid.note_taking_app.MainActivity
import com.nigdroid.note_taking_app.R
import com.nigdroid.note_taking_app.adapter.NoteAdapter
import com.nigdroid.note_taking_app.databinding.FragmentHomeBinding
import com.nigdroid.note_taking_app.databinding.FragmentUpdateNoteBinding
import com.nigdroid.note_taking_app.model.Note
import com.nigdroid.note_taking_app.viewModel.NoteViewModel


class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private var _binding: FragmentUpdateNoteBinding? = null
   private val binding get() = _binding!!
    private lateinit var notesViewModel: NoteViewModel

    private lateinit var currentNote: Note
    private val args: UpdateNoteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentUpdateNoteBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesViewModel=(activity as MainActivity).noteViewModel
        currentNote=args.note!!


        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)

        binding.fabDone.setOnClickListener {
            val title=binding.etNoteTitleUpdate.text.toString().trim()
            val body=binding.etNoteBodyUpdate.text.toString().trim()


            if (title.isNotEmpty()){
                val note=Note(currentNote.id,title,body)
                notesViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            else{
                Toast.makeText(context,"Enter a note title please",Toast.LENGTH_LONG).show()
            }
        }
    }

    private  fun deleteNote(){

       AlertDialog.Builder(activity).apply {
           setTitle("Delete Note")
           setMessage("Are you sure you want to delete this note?")

           setPositiveButton("Delete"){_,_ ->
               notesViewModel.deleteNote(currentNote)
               view?.findNavController()?.navigate(
                   R.id.action_updateNoteFragment_to_homeFragment
               )
           }
           setNegativeButton("Cancel",null)

       }.create().show()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete ->{
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }


}