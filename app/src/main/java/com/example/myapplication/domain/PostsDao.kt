package com.example.myapplication.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@androidx.room.Dao
interface PostsDao {
    @Query("SELECT * FROM Posts_entity")
    fun getPosts(): List<PostModel>

    @Delete
    fun delete(post: PostModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: PostModel)

    @Delete
    fun deleteAll(deleteAllPosts: List<PostModel>)


}