package com.example.newfacultyevaluation.ui.screens.dash.portal.admin


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlaceholderVerticalAlign.Companion.TextCenter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    viewModel: AdminViewModel = viewModel(factory = FacultyAppViewModelProvider.AdminFactory),
) {
    var searchText by remember { mutableStateOf("") }
    val users by viewModel.getAllUsers().collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search for name") },
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Black)
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            searchText = ""
                        }
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear search", tint = Color.Black)
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black, // Text color
                cursorColor = Color.Black, // Cursor color
                focusedBorderColor = Color.Black, // Border color when focused
                unfocusedBorderColor = Color.Black, // Border color when not focused

            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Do something on keyboard done */ })
        )


        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "USER LIST",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            UserColumn()
            LazyColumn {
                val filteredUsers = users?.filter { user ->
                    user.fullName!!.contains(searchText, ignoreCase = true) ||
                            user.userID.contains(searchText, ignoreCase = true)
                }?.sortedBy { it.fullName } ?: emptyList() // Sort the filtered users by name
                filteredUsers.forEachIndexed { index, user ->
                    item {
                        UserCard(user = user, index = index)
                    }
                }
//                users?.forEach {
//
//                }
            }
        }


    }

//    Spacer(Modifier.height(150.dp))
//
//    Column(
//        modifier = Modifier
//            .padding(5.dp)
//            .border(1.dp, Color.Magenta),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "USER LIST", fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier
//            .height(50.dp)
//            .fillMaxWidth()
//            .background(
//                Color.Red
//            )
//            .padding(5.dp), textAlign = TextAlign.Center, color = Color.White)
//
//            Row(
//                modifier = Modifier
//                    .horizontalScroll(rememberScrollState())
//                    .width(700.dp)
//                    .verticalScroll(rememberScrollState())
//                    .height(550.dp),
//
//                horizontalArrangement = Arrangement.SpaceBetween,
//            ) {
//
//                Column (
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .weight(1f)
//                ){
//
//                    Text(text = "User ID", textAlign = TextAlign.Center, modifier = Modifier
//                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        users.value?.forEach {
//                            Text(text = it.userID, modifier = Modifier
//                                .height(50.dp))
//                        }
//                }
//
//                Column (
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .weight(1f)
//                ){
//
//                    Text(text = "Year Level", textAlign = TextAlign.Center, modifier = Modifier
//                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                    users.value?.forEach {
//                        Text(text = it.year.toString(), modifier = Modifier
//                            .height(50.dp))
//                    }
//                }
//
//                Column (
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .weight(1f)
//                ){
//
//                    Text(text = "Program", textAlign = TextAlign.Center, modifier = Modifier
//                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                    users.value?.forEach {
//                        Text(text = it.selectedCourse.toString() , modifier = Modifier
//                            .height(50.dp))
//                    }
//                }
//
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .weight(1f)
//                ) {
//
//                    Text(text = "Name", textAlign = TextAlign.Center, modifier = Modifier
//                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                    users.value?.forEach {
//                        Text(text = "${it.fullName}", modifier = Modifier
//                            .height(50.dp))
//                    }
//                }
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .weight(1f)
//                ) {
//
//                    Text(text = "Role", textAlign = TextAlign.Center, modifier = Modifier
//                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                    users.value?.forEach {
//                        Text(text = it.role, modifier = Modifier
//                            .height(50.dp))
//                    }
//                }
//
//
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .padding(10.dp)
//                ) {
//
//                    Text(text = "Action", textAlign = TextAlign.Center, modifier = Modifier
//                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
//
//                    var showEdit by remember {
//                        mutableStateOf(false)
//                    }
//
//                    var selectedUser by remember {
//                        mutableStateOf(User("", "","","","","", ""))
//                    }
//
//
//                    users.value?.forEach { user ->
//                        Row(
//                            modifier = Modifier
//                                .width(100.dp)
//                                .height(50.dp)
//                        ){
//                            Icon(Icons.Filled.Edit, contentDescription = "Edit",modifier = Modifier
//                                .clickable {
//                                    showEdit = true
//                                    selectedUser = user
//                                }
//                                .weight(1f)
//                            )
//                            Icon(Icons.Filled.Delete, contentDescription = "Delete",modifier = Modifier
//                                .weight(1f))
//                        }
//
//                        if(showEdit){
//                            Dialog(onDismissRequest = { }) {
//
//                                Column(
//                                    modifier = Modifier
//                                        .background(Color.White)
//                                        .height(500.dp)
//                                        .padding(20.dp),
//                                    verticalArrangement = Arrangement.SpaceEvenly,
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(text = "User Info", fontWeight = FontWeight.Bold, fontSize = 25.sp)
//                                    OutlinedTextField(value = selectedUser.userID, onValueChange = { }, enabled = false)
//                                    OutlinedTextField(value = selectedUser.fullName.toString(), onValueChange = { }, enabled = false)
//                                    Row(){
//                                        var clicked by remember {
//                                            mutableStateOf(false)
//                                        }
//
//                                        OutlinedTextField(
//                                            value = selectedUser.password,
//                                            onValueChange = { },
//                                            label = {Text("Password")},
//                                            visualTransformation = if(!clicked) PasswordVisualTransformation() else VisualTransformation.None,
//                                            trailingIcon = { Icon(imageVector = if(clicked) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = "Password", modifier = Modifier.clickable { clicked =!clicked })}
//                                        )
//
//                                    }
//                                    var showChangePass by remember {
//                                        mutableStateOf(false)
//                                    }
//                                    Row(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        horizontalArrangement = Arrangement.End
//                                    ){
//
//                                        Text("Change Password", modifier = Modifier.clickable {
//                                            showChangePass = true
//                                        })
//                                    }
//                                    if(showChangePass){
//                                        var changePass by remember {
//                                            mutableStateOf("")
//                                        }
//                                        Dialog(onDismissRequest = { }) {
//                                            Column(
//                                                modifier = Modifier
//                                                    .background(Color.White)
//                                                    .height(200.dp),
//                                                horizontalAlignment = Alignment.CenterHorizontally,
//                                                verticalArrangement = Arrangement.SpaceAround
//                                            ) {
//
//                                                OutlinedTextField(value = changePass, onValueChange = { changePass = it }, label= { Text("Change Password")})
//                                                Row(
//                                                    modifier = Modifier.fillMaxWidth(),
//                                                    horizontalArrangement = Arrangement.SpaceAround,
//                                                    verticalAlignment = Alignment.CenterVertically
//                                                ){
//                                                    val context = LocalContext.current
//                                                    Button(onClick = {
//                                                        showChangePass = false
//                                                        if(changePass == selectedUser.password){
//                                                            Toast.makeText(context, "Same Password cannot be changed", Toast.LENGTH_SHORT).show()
//                                                        }else {
//                                                            viewModel.updateUser(user = selectedUser.copy(password = changePass))
//                                                            when(selectedUser.role){
//                                                                "Faculty" -> viewModel.updateFaculty(faculty = Faculty(selectedUser.userID,selectedUser.fullName.toString(),changePass))
//                                                                "Student" -> viewModel.updateStudent(student = Student(selectedUser.userID,selectedUser.fullName.toString(),changePass,selectedUser.selectedCourse,selectedUser.role,selectedUser.dateCreated.toString(), selectedUser.year.toString()))
//                                                            }
//                                                            Toast.makeText(context, "Password Successfully changed!", Toast.LENGTH_SHORT).show()
//                                                        }
//                                                    }) {
//                                                        Text(text = "Change")
//                                                    }
//                                                    Button(onClick = {
//                                                        showChangePass = false
//                                                    }, colors = ButtonDefaults.buttonColors(
//                                                        containerColor = Color.LightGray,
//                                                        contentColor = Color.Black
//                                                    )) {
//                                                        Text(text = "Cancel")
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                    Row (
//                                        horizontalArrangement = Arrangement.SpaceAround,
//                                        modifier = Modifier.fillMaxWidth()
//                                    ){
//                                        Button(onClick = {
//                                            showEdit = false
//        //                                    viewModel.updateUser(user = User())
//                                        }) {
//                                            Text(text = "Save")
//                                        }
//                                        Button(onClick = {
//                                            showEdit = false
//                                        }, colors = ButtonDefaults.buttonColors(
//                                            containerColor = Color.LightGray,
//                                            contentColor = Color.Black
//                                        )) {
//                                            Text(text = "Cancel")
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//
//
//
//            }
//
//        }

    }


