package com.nbscollege.facultyevaluation.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nbscollege.facultyevaluation.model.data.LoginReq
import com.nbscollege.facultyevaluation.model.data.RegistrationReq
import com.nbscollege.facultyevaluation.model.user.dao.UserDao


/**
     * Database class with a singleton Instance object.
     */
    @Database(entities = [LoginReq::class], version = 1, exportSchema = true)
    abstract class FacultyAppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao

        companion object {
            @Volatile
            private var Instance: FacultyAppDatabase? = null
            fun getDatabase(context: Context): FacultyAppDatabase {
                // if the Instance is not null, return it, otherwise create a new database instance.
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, FacultyAppDatabase::class.java, "nbs_faculty_app")
                        /**
                         * Setting this option in your app's database builder means that Room
                         * permanently deletes all data from the tables in your database when it
                         * attempts to perform a migration with no defined migration path.
                         */
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { Instance = it }
                }
            }
        }
    }
