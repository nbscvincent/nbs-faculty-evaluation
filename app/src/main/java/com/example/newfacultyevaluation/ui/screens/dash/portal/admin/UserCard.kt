package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
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
fun UserCard(user: User, index: Int) {

    Card(
        colors = CardDefaults.cardColors(
            if (index % 2 == 0) Color.Transparent else Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    user.fullName.toString(),
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Text(
                    user.year.toString(),
                    modifier = Modifier.weight(1.5f),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Text(
                    user.selectedCourse.toString(),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Text(
                    user.role.toString(),
                    modifier = Modifier.weight(1.3f),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                val viewModel: AdminViewModel = viewModel(factory = FacultyAppViewModelProvider.AdminFactory)
                val users = viewModel.getAllUsers().collectAsState(initial = null)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(1.dp)
                        .weight(1f)
                ) {
                    var showEdit by remember { mutableStateOf(false) }
                    var selectedUser by remember { mutableStateOf(User("", "", "", "", "", "", "")) }
                    val context = LocalContext.current
                    var showDelete by remember { mutableStateOf(false) }

                    Row(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        verticalAlignment = Alignment.CenterVertically // Align icons vertically center
                    ) {
                        // Edit button
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.weight(1f)
                                .clickable {
                                    showEdit = true
                                    selectedUser = user
                                },
                            tint = Color.Black
                        )

                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.weight(1f)
                                .clickable {
                                    showDelete = true
                                    selectedUser = user
                                },
                                    tint = Color.Black
                        )
                    }

                    // Edit dialog
                    if (showEdit) {
                        Dialog(onDismissRequest = { }) {
                            Column(
                                modifier = Modifier
                                    .background(Color.White)
                                    .height(500.dp)
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "User Info", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.Black)

                                OutlinedTextField(
                                    value = selectedUser.userID,
                                    onValueChange = { },
                                    label = { Text("UserID" , color = Color.Black) },
                                    enabled = false,
                                    textStyle = TextStyle(color = Color.Black),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        disabledBorderColor = Color.Black // Change the border color to black
                                    )
                                )
                                OutlinedTextField(
                                    value = selectedUser.fullName.toString(),
                                    onValueChange = { },
                                    label = { Text("Name:", color = Color.Black) },
                                    enabled = false,
                                    textStyle = TextStyle(color = Color.Black),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        disabledBorderColor = Color.Black // Change the border color to black
                                    )

                                )
                                Row() {
                                    var clicked by remember {
                                        mutableStateOf(false)
                                    }

                                    OutlinedTextField(
                                        value = selectedUser.password,
                                        onValueChange = { },
                                        label = { Text("Password", color = Color.Black) },
                                        visualTransformation = if (!clicked) PasswordVisualTransformation() else VisualTransformation.None,
                                        enabled = false,
                                        textStyle = TextStyle(color = Color.Black), // Change the text color to black
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            disabledBorderColor = Color.Black // Change the border color to black
                                        ),
                                        trailingIcon = {
                                            Icon(
                                                imageVector = if (clicked) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                                contentDescription = "Password",
                                                modifier = Modifier.clickable { clicked = !clicked },
                                                tint = Color.Black
                                            )
                                        }
                                    )

                                }
                                var showChangePass by remember {
                                    mutableStateOf(false)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {

                                    Text("Change Password", modifier = Modifier.clickable {
                                        showChangePass = true
                                    }, color = Color.Black)
                                }
                                if (showChangePass) {
                                    var changePass by remember {
                                        mutableStateOf("")
                                    }

                                    val yearOptions = listOf("Select Year:", "1st", "2nd", "3rd", "4th")
                                    var changeYear by rememberSaveable {
                                        mutableStateOf(yearOptions[0])
                                    }

                                    var expanded2 by rememberSaveable {
                                        mutableStateOf(false)
                                    }



                                    Dialog(onDismissRequest = { }) {
                                        Column(
                                            modifier = Modifier
                                                .background(Color.White)
                                                .height(200.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.SpaceAround
                                        ) {
                                            OutlinedTextField(
                                                value = changePass,
                                                onValueChange = { changePass = it },
                                                label = { Text("Change Password") })
                                            Spacer(modifier = Modifier.height(20.dp))
//                                            Row(
//                                                modifier = Modifier.weight(1f).clickable {
//                                                    expanded2 = !expanded2
//                                                },
//                                                horizontalArrangement = Arrangement.Center
//                                            ) {
//
//                                                Text(text = changeYear, color = Color.Black)
//                                                Icon(
//                                                    imageVector = if (expanded2) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
//                                                    contentDescription = "",
//                                                    tint = Color.Black
//                                                )
//
//                                            }
//
//                                            DropdownMenu(
//                                                expanded = expanded2,
//                                                onDismissRequest = { expanded2 = false },
//                                                modifier = Modifier
//                                                    .background(Color.White),
//                                                offset = DpOffset(x = 100.dp, y = -100.dp)
//                                            ) {
//                                                yearOptions.forEach { r ->
//                                                    DropdownMenuItem(
//                                                        text = { Text(text = r) },
//                                                        onClick = { changeYear = r; expanded2 = false },
//                                                        enabled = r != yearOptions[0]
//                                                    )
//                                                }
//                                            }

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceAround,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                val context = LocalContext.current
                                                Button(onClick = {
                                                    showChangePass = false
                                                    if (changePass == selectedUser.password) {
                                                        Toast.makeText(
                                                            context,
                                                            "Same Password cannot be changed",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    } else {
//                                                        viewModel.updateUser(
//                                                            user = selectedUser.copy(
//                                                                password = changePass,
//                                                                year = changeYear
//                                                            )
//                                                        )
//                                                        when (selectedUser.role) {
//                                                            "Faculty" -> viewModel.updateFaculty(
//                                                                faculty = Faculty(
//                                                                    selectedUser.userID.toString(),
//                                                                    selectedUser.fullName.toString(),
//                                                                    changePass,
//
//                                                                )
//                                                            )
//
//                                                            "Student" -> viewModel.updateStudent(
//                                                                student = Student(
//                                                                    selectedUser.userID.toString(),
//                                                                    selectedUser.fullName.toString(),
//                                                                    changePass,
//                                                                    selectedUser.selectedCourse,
//                                                                    selectedUser.role,
//                                                                    changeYear,
//                                                                    selectedUser.dateCreated.toString(),
//
//                                                                )
//                                                            )
//                                                        }
//                                                        Toast.makeText(
//                                                            context,
//                                                            "Password Successfully changed!",
//                                                            Toast.LENGTH_SHORT
//                                                        ).show()
                                                    }
                                                }) {
                                                    Text(text = "Change")
                                                }
                                                Button(
                                                    onClick = {
                                                        showChangePass = false
                                                    }, colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color.LightGray,
                                                        contentColor = Color.Black
                                                    )
                                                ) {
                                                    Text(text = "Cancel")
                                                }
                                            }
                                        }
                                    }
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Button(onClick = {
                                        showEdit = false
                                        //                                    viewModel.updateUser(user = User())
                                    }) {
                                        Text(text = "Save")
                                    }
                                    Button(
                                        onClick = {
                                            showEdit = false
                                        }, colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.LightGray,
                                            contentColor = Color.Black
                                        )
                                    ) {
                                        Text(text = "Cancel")
                                    }
                                }
                            }
                        }
                    }
                    if(showDelete) {
                        Dialog(onDismissRequest = { showDelete = false }) {
                            Column(
                                modifier = Modifier
                                    .background(Color.White)
                                    .height(200.dp)
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "Delete User ${selectedUser.fullName} ?",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Button(
                                        onClick = {

//                                            viewModel.deleteUser(selectedUser)

                                            Toast.makeText(
                                                context,
                                                "User Deleted",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            showDelete = false // Dismiss the dialog after deletion

                                            Toast.makeText(
                                                context,
                                                "User Deleted",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    ) {
                                        Text(text = "Yes")
                                    }
                                    Button(
                                        onClick = { showDelete = false }, // Dismiss the dialog when Cancel is clicked
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.LightGray,
                                            contentColor = Color.Black
                                        )
                                    ) {
                                        Text(text = "Cancel")
                                    }
                                }
                            }
                        }
                    }


                }

            }
        }
    }
}

@Composable
fun UserColumn() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth() .height(60.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Text(
                    "Name",
                    modifier = Modifier.weight(2f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,

                )
                Text(
                    "Year",
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Program",
                    color = Color.White,
                    modifier = Modifier.weight(1.5f),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    "Role",
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Action",
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}



