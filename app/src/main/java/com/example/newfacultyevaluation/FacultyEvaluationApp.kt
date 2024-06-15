package com.example.newfacultyevaluation

import android.app.Application
import com.example.newfacultyevaluation.data.FacultyAppContainer
import com.example.newfacultyevaluation.data.FacultyAppDataContainer

class FacultyEvaluationApp : Application() {

    lateinit var container: FacultyAppContainer

    override fun onCreate() {
        super.onCreate()
        container = FacultyAppDataContainer(this)
    }

}