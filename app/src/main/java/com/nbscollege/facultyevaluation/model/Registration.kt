import android.annotation.SuppressLint
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbscollege.facultyevaluation.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(){
    var studentNo by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TextButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(10.dp),
                    tint = Color.White) }
            },
        modifier = Modifier.padding(12.dp),
        containerColor = Color.Transparent) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp, 200.dp)
                    .align(Alignment.TopEnd))
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(top = 50.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(translationY = 170f)) {
                        OutlinedTextField(
                            value = studentNo,
                            onValueChange = {studentNo = it},
                            placeholder = {Text(text = "Student Number", fontSize = 30.sp, textAlign = TextAlign.Center)},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(fontSize = 30.sp, letterSpacing = 4.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 50.dp, end = 50.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black

                            ))

                        OutlinedTextField(
                            value = firstName,
                            onValueChange = {firstName = it},
                            placeholder = {Text(text = "First Name", fontSize = 30.sp, textAlign = TextAlign.Center)},
                            textStyle = TextStyle(fontSize = 30.sp, letterSpacing = 4.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 50.dp, end = 50.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ))

                        OutlinedTextField(
                            value = lastName,
                            onValueChange = {lastName = it},
                            placeholder = {Text(text = "Last Name", fontSize = 30.sp, textAlign = TextAlign.Center)},
                            textStyle = TextStyle(fontSize = 30.sp, letterSpacing = 4.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 50.dp, end = 50.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ))

                        OutlinedTextField(
                            value = email,
                            onValueChange = {email = it},
                            placeholder = {Text(text = "Email", fontSize = 30.sp, textAlign = TextAlign.Center)},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            textStyle = TextStyle(fontSize = 30.sp, letterSpacing = 4.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 50.dp, end = 50.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ))

                        OutlinedTextField(
                            value = password,
                            onValueChange = {password = it},
                            placeholder = {Text(text = "Password", fontSize = 30.sp, textAlign = TextAlign.Center)},
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            textStyle = TextStyle(fontSize = 30.sp, letterSpacing = 5.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 50.dp, end = 50.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ))
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(700.dp, 70.dp)
                        .padding(start = 80.dp, end = 80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )) {
                    Text(text = "REGISTER", fontSize = 30.sp)
                }

            }

        }
    }

}