package com.example.myapplication

import androidx.room.Room
import androidx.room.RoomDatabase


@androidx.room.Database(entities = [Posts::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): PostsDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDataBase(context: MainActivity): Database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, Database::class.java, "Posts_Data").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}




