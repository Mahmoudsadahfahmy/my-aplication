package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Database
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show, container, false)
        val btnNavigateToAdd: Button = view.findViewById(R.id.btnNavigateToAdd)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv)
        val database = Database.getDataBase(this.requireContext().applicationContext)
        val deleteAll: Button = view.findViewById(R.id.btnDeleteAll)
        val navController = findNavController()

        btnNavigateToAdd.setOnClickListener {
            navController.navigate(R.id.action_showFragment2_to_addFragment2)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val data = database.userDao().getPosts()
            val recyclerRoom =
                RecyclerRoom(data, database, recyclerView, navController, findNavController())
            recyclerView.adapter = recyclerRoom
            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        deleteAll.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                database.userDao().deleteAll(database.userDao().getPosts())
                val data = database.userDao().getPosts()
                val recyclerRoom = RecyclerRoom(
                    data,
                    database,
                    recyclerView,
                    navController,
                    findNavController()
                )
                recyclerView.adapter = recyclerRoom
            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        return view
    }
}

        