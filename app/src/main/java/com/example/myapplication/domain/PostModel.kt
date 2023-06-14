package com.example.myapplication.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts_entity")
  data class PostModel(
    @PrimaryKey(true)
    var id: Int = 0,
     var age: Int? = null,
     var number: Int? = null,
     var name: String? = null,
)











