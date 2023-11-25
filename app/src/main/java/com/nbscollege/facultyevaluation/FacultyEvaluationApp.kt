package com.nbscollege.facultyevaluation

import android.app.Application
import com.nbscollege.facultyevaluation.model.data.AppContainer
import com.nbscollege.facultyevaluation.model.data.AppDataContainer

class FacultyEvaluationApp : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}