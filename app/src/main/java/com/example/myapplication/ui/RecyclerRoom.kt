package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Database
import com.example.myapplication.R
import com.example.myapplication.domain.PostModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerRoom(
    private val data: List<PostModel>,
    private val database: Database,
    private val recyclerView: RecyclerView,
    private val navController: NavController,
    private val findNavController: NavController,

    ) : RecyclerView.Adapter<RecyclerRoom.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.nameText)
        val ageText: TextView = itemView.findViewById(R.id.ageText)
        val numberText: TextView = itemView.findViewById(R.id.numberText)
        val idText: TextView = itemView.findViewById(R.id.IdText)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CustomViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val postReplace = data[position]
        holder.nameText.text = postReplace.name.toString()
        holder.ageText.text = postReplace.age.toString()
        holder.numberText.text = postReplace.number.toString()
        holder.idText.text = postReplace.id.toString()

        holder.deleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                database.userDao().delete(postReplace)
                val data = database.userDao().getPosts()
                val recyclerRoom =
                    RecyclerRoom(data, database, recyclerView, findNavController, navController)
                recyclerView.adapter = recyclerRoom
            }
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)


        }

    }
}




