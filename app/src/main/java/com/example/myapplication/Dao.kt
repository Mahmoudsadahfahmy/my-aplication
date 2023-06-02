package com.example.myapplication

import androidx.room.Insert
import okhttp3.internal.connection.RouteSelector.Selection
import retrofit2.http.Query

@androidx.room.Dao
interface Dao {
    @androidx.room.Query("SELECT * FROM Posts")
    fun getPosts():List<Posts>

    @Insert
    fun insertAll(vararg users: Posts)
}