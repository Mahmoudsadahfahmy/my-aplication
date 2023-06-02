package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Posts_entity")
data class Posts(var name: String,@PrimaryKey(true)var id :Int,val age :Int,var number:String)

