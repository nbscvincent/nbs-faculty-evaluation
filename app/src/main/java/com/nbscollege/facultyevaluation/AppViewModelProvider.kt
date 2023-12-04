package com.nbscollege.facultyevaluation

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nbscollege.facultyevaluation.registration.viewmodel.RegistrationViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for RegistrationViewModel
        initializer {
            RegistrationViewModel(
                facultyEvaluationApp().container.userRepository
            )
        }

    }
}
/**
 * Extension function to queries for [Application] object and returns an instance of
 * [NBSApplication].
 */
fun CreationExtras.facultyEvaluationApp(): FacultyEvaluationApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FacultyEvaluationApp)
