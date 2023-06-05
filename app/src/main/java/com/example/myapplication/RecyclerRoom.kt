package com.example.myapplication

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerRoom(val posts: List<Posts>) : RecyclerView.Adapter<RecyclerRoom.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
      val textAge : TextView =itemView.findViewById(R.id.textView5)
        val textNumber :TextView =itemView.findViewById(R.id.textView4)
        val textName :TextView =itemView.findViewById(R.id.textView6)


    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerRoom.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CustomViewHolder(view)    }

    override fun onBindViewHolder(holder: RecyclerRoom.CustomViewHolder, position: Int) {
        holder.textAge.text=posts.get(position).age.toString()
        holder.textName.text=posts.get(position).name
        holder.textNumber.text=posts.get(position).number.toString()
    }

    override fun getItemCount(): Int {
      return posts.size
    }
}