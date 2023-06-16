package com.example.myapplication.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.google.android.material.textfield.TextInputEditText
import java.util.jar.Attributes.Name

@androidx.room.Dao
interface PostsDao {
    @Query("SELECT * FROM Posts_entity")
    fun getPosts():List<PostModel>

    @Delete
    fun delete(post: PostModel)

    @Insert
    fun insert(posts: PostModel)

    @Delete
    fun deleteAll(deleteAllPosts:List<PostModel>)

    @Query("UPDATE Posts_entity SET name=:name,age=:age,number=:number WHERE id LIKE :id")
    suspend fun update(name : String,age:Int,number:Int,id:Int)

}