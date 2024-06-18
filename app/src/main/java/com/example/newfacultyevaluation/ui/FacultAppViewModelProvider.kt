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
                facultyEvaluationApp().container.onlineUserRepository,
                facultyEvaluationApp().container.onlineStudentRepository


            )
        }
    }

    val LoginFactory = viewModelFactory {
        initializer {
            LoginViewModel(
                facultyEvaluationApp().container.onlineUserRepository
            )
        }
    }

    val AdminFactory = viewModelFactory {
        initializer {
            AdminViewModel(
                facultyEvaluationApp().container.onlineUserRepository
            )
        }
    }

    val FacultyFactory = viewModelFactory {
        initializer {
            FacultyViewModel(
//                facultyEvaluationApp().container.facultyRepository
            )
        }
    }
    val StudentFactory = viewModelFactory {
        initializer {
            StudentViewModel(
                facultyEvaluationApp().container.onlineStudentRepository
            )
        }
    }




}

fun CreationExtras.facultyEvaluationApp() : FacultyEvaluationApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FacultyEvaluationApp)