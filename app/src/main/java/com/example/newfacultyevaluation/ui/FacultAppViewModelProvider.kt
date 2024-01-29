package com.example.newfacultyevaluation.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newfacultyevaluation.FacultyEvaluationApp
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import com.example.newfacultyevaluation.ui.screens.auth.RegisterViewModel
import com.example.newfacultyevaluation.ui.screens.dash.portal.admin.AdminViewModel
import com.example.newfacultyevaluation.ui.screens.dash.portal.faculty.FacultyViewModel
import com.example.newfacultyevaluation.ui.screens.dash.portal.student.StudentViewModel

object FacultyAppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            RegisterViewModel(
                facultyEvaluationApp().container.facultyRepository,
                facultyEvaluationApp().container.userRepository,
                facultyEvaluationApp().container.studentRepository

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

    val AdminFactory = viewModelFactory {
        initializer {
            AdminViewModel(
                facultyEvaluationApp().container.userRepository,
                facultyEvaluationApp().container.adminRepository,
                facultyEvaluationApp().container.studentRepository,
                facultyEvaluationApp().container.facultyRepository
            )
        }
    }

    val FacultyFactory = viewModelFactory {
        initializer {
            FacultyViewModel(
                facultyEvaluationApp().container.facultyRepository
            )
        }
    }
    val StudentFactory = viewModelFactory {
        initializer {
            StudentViewModel(
                facultyEvaluationApp().container.studentRepository
            )
        }
    }




}

fun CreationExtras.facultyEvaluationApp() : FacultyEvaluationApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FacultyEvaluationApp)