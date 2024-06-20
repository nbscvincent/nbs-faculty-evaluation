package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.FacultyAppContainer
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.AdminNav

@Composable
fun EditForm(
    navController: NavController,
    viewModel: AdminViewModel = viewModel(factory = FacultyAppViewModelProvider.AdminFactory)
) {

    BackHandler {
        navController.popBackStack()
        navController.navigate(AdminNav.UserList.name)
    }

    val questions by viewModel.getAllQuestions().collectAsState(initial = emptyList())

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        questions.forEach { question ->

            EditFormCard(question = question, viewModel, navController)

        }
    }
    

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditFormCard(question: Question, viewModel: AdminViewModel, navController: NavController) {
    var edit by remember {
        mutableStateOf(false)
    }
    var text by remember {
        mutableStateOf(question.question)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(text = question.question)
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.End
            ){

                Button(onClick = {
                    edit = true
                }) {
                    Text(text = "Edit")
                }
            }
        }

    }
    if(edit){
        Dialog(onDismissRequest = { edit = false }) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Edit Question", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Unspecified,
                        containerColor = Color.White
                    )
                )
                Row (
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End)
                ){
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            edit = false
                            viewModel.updateQuestion(Question(id = question.id, question = text))
                            navController.popBackStack()
                            navController.navigate(AdminNav.EditForm.name)
                            Toast.makeText(context, "Question Updated", Toast.LENGTH_SHORT).show()

                        }
                    ) {
                        Text(text = "Save")
                    }
                    Button(
                        onClick = { edit = false},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}