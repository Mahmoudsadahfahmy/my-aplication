package com.example.myapplication.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Database
import com.example.myapplication.R
import com.example.myapplication.RecyclerRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ShowFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv)
        val database = Database.getDataBase(this.requireContext().applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            val data = database.userDao().getPosts()
            val recyclerRoom = RecyclerRoom(data)
            recyclerView.adapter=recyclerRoom
        }
    recyclerView.layoutManager= LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        return view
    }


}