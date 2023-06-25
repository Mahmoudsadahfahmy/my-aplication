package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.*
import com.example.myapplication.domain.Database
import com.example.myapplication.domain.PostModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddFragment : Fragment() {
    lateinit var addName: TextInputEditText
    lateinit var addAge: TextInputEditText
    lateinit var addNumber: TextInputEditText
    lateinit var post: PostModel
    lateinit var database: Database
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        database = Database.getDataBase(this.requireContext().applicationContext)
        post = PostModel()
        addName = view.findViewById(R.id.addName)
        addAge = view.findViewById(R.id.addAge)
        addNumber = view.findViewById(R.id.addNumber)
        val save: Button = view.findViewById(R.id.save_button)

        save.setOnClickListener {
            test()
        }
        return view
    }

    private fun test() {
        if (addName.text.toString().isEmpty() && addAge.text.toString()
                .isEmpty() && addNumber.text.toString().isEmpty()
        ) {
            Toast.makeText(this.context, "Add all values", Toast.LENGTH_SHORT).show()
        } else if (addName.text.toString().isEmpty()) {
            Toast.makeText(this.context, "Add name", Toast.LENGTH_SHORT).show()
        } else if (addAge.text.toString().isEmpty()) {
            Toast.makeText(this.context, "Add age", Toast.LENGTH_SHORT).show()
        } else if (addNumber.text.toString().isEmpty()) {
            Toast.makeText(this.context, "Add number", Toast.LENGTH_SHORT).show()

        } else {
            post.name = addName.text.toString()
            post.age = addAge.text.toString().toInt()
            post.number = addNumber.text.toString().toInt()
            CoroutineScope(Dispatchers.IO).launch {
                database.userDao().insert(post)
            }
            findNavController().popBackStack()
        }
    }
}


