package com.example.myapplication

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@androidx.room.Dao
interface PostsDao {
    @Query("SELECT * FROM Posts_entity")
    fun getPosts():List<PostModel>

    @Delete
    fun delete(post: PostModel)

    @Insert
    fun insert(posts: PostModel)
}