package com.example.newfacultyevaluation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newfacultyevaluation.dao.UserDao
import com.example.newfacultyevaluation.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class FacultyEvaluationDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    companion object{
        @Volatile
        private var Instance: FacultyEvaluationDatabase? = null

        fun getDatabase(context: Context): FacultyEvaluationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FacultyEvaluationDatabase::class.java, "FacultyAppDatabase")


                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}