package com.example.newfacultyevaluation.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newfacultyevaluation.FacultyEvaluationApp
import com.example.newfacultyevaluation.ui.screens.SplashViewModel
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import com.example.newfacultyevaluation.ui.screens.auth.RegisterViewModel

object FacultyAppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            RegisterViewModel(
                this.facultyEvaluationApp().container.userRepository
            )
        }
    }

    val LoginFactory = viewModelFactory {
        initializer {
            LoginViewModel(
                facultyEvaluationApp().container.userRepository
            )
        }
    }

    val SplashFactory = viewModelFactory {
        initializer {
            SplashViewModel(
                facultyEvaluationApp().container.userRepository
            )
        }
    }

}

fun CreationExtras.facultyEvaluationApp() : FacultyEvaluationApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FacultyEvaluationApp)