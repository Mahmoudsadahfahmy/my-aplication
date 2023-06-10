package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class RecyclerRoom(val data: List<PostModel>) : RecyclerView.Adapter<RecyclerRoom.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameText : TextView = itemView.findViewById(R.id.nameText)
        val ageText : TextView = itemView.findViewById(R.id.ageText)
        val numberText : TextView = itemView.findViewById(R.id.numberText)
        val deleteButton:Button =itemView.findViewById(R.id.deleteButton)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerRoom.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CustomViewHolder(view)    }

     override fun getItemCount(): Int {
         return data.size
     }

     override fun onBindViewHolder(holder: RecyclerRoom.CustomViewHolder, position: Int) {
     val postReplace=data[position]
         holder.nameText.text =postReplace.name.toString()
         holder.ageText.text = postReplace.age.hashCode().toString()
         holder.numberText.text = postReplace.number.toString()



        }
    }


