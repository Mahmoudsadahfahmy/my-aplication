package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Database
import com.example.myapplication.domain.PostModel
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerRoom(
    val data: List<PostModel>,
    val database: Database,
    val recyclerView: RecyclerView,
    val navController: NavController,
    val findNavController: NavController
) : RecyclerView.Adapter<RecyclerRoom.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameText : TextView = itemView.findViewById(R.id.nameText)
        val ageText : TextView = itemView.findViewById(R.id.ageText)
        val numberText : TextView = itemView.findViewById(R.id.numberText)
        val deleteButton:Button =itemView.findViewById(R.id.deleteButton)
        val changeButton:Button =itemView.findViewById(R.id.changeButton)


    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CustomViewHolder(view)    }

     override fun getItemCount(): Int {
         return data.size
     }

     override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
     val postReplace=data[position]
         holder.nameText.text =postReplace.name.toString()
         holder.ageText.text = postReplace.age.toString().toInt().toString()
         holder.numberText.text = postReplace.number.toString().toInt().toString()

         holder.deleteButton.setOnClickListener {
             CoroutineScope(Dispatchers.IO).launch {
                 database.userDao().delete(postReplace)
                 val data = database.userDao().getPosts()
                 val recyclerRoom = RecyclerRoom(
                     data,
                     database,
                     recyclerView,
                     navController,
                     findNavController,)
                 recyclerView.adapter = recyclerRoom
             }
             recyclerView.layoutManager =LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
         }
         holder.changeButton.setOnClickListener {view ->
             view.findNavController().navigate(R.id.action_showFragment2_to_addFragment2)

         }


        }


}


