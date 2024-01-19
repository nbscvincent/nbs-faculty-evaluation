package com.example.newfacultyevaluation.ui.screens.auth

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Timer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPass(
    loginViewModel: LoginViewModel,
    navController: NavController,
    viewModel: ForgotPassViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var otp by remember {
            mutableStateOf("")
        }
        var isClicked by remember {
            mutableStateOf(false)
        }
        val timer by viewModel.timer.collectAsState()
        val user by viewModel.getUser(loginViewModel.userID).observeAsState()
        val context = LocalContext.current
        Row {

            OutlinedTextField(
                value = otp.uppercase(),
                onValueChange = { otp = it },
                singleLine = true,
            )
            Button(onClick = {
                isClicked = true
                user?.copy(password = otp)?.let { viewModel.upsertUser(it) }

            }) {
                Text(text = "Send OTP")
            }
        }
        if(isClicked){
            viewModel.startTimer()
            Text(text = timer.formatTime())
        }
    }
}

fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}