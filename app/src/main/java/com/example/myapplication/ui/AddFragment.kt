package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        val addId: TextInputEditText = view.findViewById(R.id.addId)
        val updateButton: Button = view.findViewById(R.id.update_button)
       val save:Button = view.findViewById(R.id.save_button)

        save.setOnClickListener {
            if (addName.text.toString().isEmpty() && addAge.text.toString()
                    .isEmpty() && addNumber.text.toString().isEmpty() && addId.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this.context, "Add all values", Toast.LENGTH_SHORT).show()
            } else if (addName.text.toString().isEmpty()) {
                Toast.makeText(this.context, "Add name", Toast.LENGTH_SHORT).show()
            } else if (addAge.text.toString().isEmpty()) {
                Toast.makeText(this.context, "Add age", Toast.LENGTH_SHORT).show()
            } else if (addNumber.text.toString().isEmpty()) {
                Toast.makeText(this.context, "Add number", Toast.LENGTH_SHORT).show()
            } else if (addId.text.toString().isEmpty()) {
                Toast.makeText(this.context, "Add id", Toast.LENGTH_SHORT).show()
            } else {
                post.name = addName.text.toString()
                post.age = addAge.text.hashCode()
                post.number = addNumber.text.hashCode()
                post.id = addId.text.toString().hashCode()
                CoroutineScope(Dispatchers.IO).launch {
                    database.userDao().insert(post)
                }
                findNavController().popBackStack()
            }
        }
        updateButton.setOnClickListener {
            val name = addName.text.toString()
            val age = addAge.text.toString().toInt()
            val number = addNumber.text.toString().toInt()
            val id = addId.text.toString().toInt()

            CoroutineScope(Dispatchers.IO).launch {
                database.userDao().update(name, age, number, id)
            }
            findNavController().popBackStack()
        }
        return view
    }
}