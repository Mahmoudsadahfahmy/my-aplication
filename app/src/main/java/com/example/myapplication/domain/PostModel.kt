package com.example.myapplication.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts_entity")
data class PostModel(
    @PrimaryKey(true)
    @ColumnInfo("id_post")
    var id: Int? = null,
    var age: Int? = null,
    var number: Int? = null,
    var name: String? = null,
)











