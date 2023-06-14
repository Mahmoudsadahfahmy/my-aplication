package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myapplication.*
import com.example.myapplication.domain.Database
import com.example.myapplication.domain.PostModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddFragment : Fragment() {

    lateinit var save: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val post = PostModel()
        val database = Database.getDataBase(this.requireContext().applicationContext)
        val addName: TextInputEditText = view.findViewById(R.id.addName)
        val addAge: TextInputEditText = view.findViewById(R.id.addAge)
        val addNumber: TextInputEditText = view.findViewById(R.id.addNumber)

        save = view.findViewById(R.id.save_button)
        save.setOnClickListener {
            post.name = addName.text.toString()
            post.age = addAge.text.toString().toInt()
            post.number = addNumber.text.toString().toInt()

            CoroutineScope(Dispatchers.IO).launch {
                database.userDao().insert(post)
            }
            findNavController().popBackStack()
        }
        return view
    }
}