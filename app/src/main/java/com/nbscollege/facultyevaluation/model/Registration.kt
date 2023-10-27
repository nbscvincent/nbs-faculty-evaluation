import android.annotation.SuppressLint
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbscollege.facultyevaluation.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.model.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(navController: NavController){
    var studentNo by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TextButton(onClick = {
                navController.navigate(Screen.HomeScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    tint = Color.White) }
            },
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
                .zIndex(1f)
                .graphicsLayer(translationY = 200f, translationX = -350f)
        ){

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color = Color(0x33000000)),
                modifier = Modifier
                    .size(1500.dp, 2000.dp)
                    .rotate(56.03f)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(top = 50.dp)
                .zIndex(2f),
            contentAlignment = Alignment.BottomCenter
        ){

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(translationY = 170f)) {
                        Spacer(modifier = Modifier.height(50.dp))
//                Student Number TextField
                        OutlinedTextField(
                            value = studentNo,
                            onValueChange = {studentNo = it},
                            placeholder = {Text(text = "Student Number", fontSize = 20.sp, textAlign = TextAlign.Center)},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 3.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            singleLine = true,
                            
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black

                            ))
//                  First Name TextField
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = {firstName = it},
                            placeholder = {Text(text = "First Name", fontSize = 20.sp, textAlign = TextAlign.Center)},
                            textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 3.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ))
//                Last Name TextField
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = {lastName = it},
                            placeholder = {Text(text = "Last Name", fontSize = 20.sp, textAlign = TextAlign.Center)},
                            textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 3.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ))
//                  Email TextField
                        OutlinedTextField(
                            value = email,
                            onValueChange = {email = it},
                            placeholder = {Text(text = "Email", fontSize = 20.sp, textAlign = TextAlign.Center)},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 3.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ))
//                  Password TextField
                        OutlinedTextField(
                            value = password,
                            onValueChange = {password = it},
                            placeholder = {Text(text = "Password", fontSize = 20.sp, textAlign = TextAlign.Center)},
                            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 3.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black
                            ),
                            trailingIcon = {

                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                // Localized description for accessibility services
                                val description = if (passwordVisible) "Hide password" else "Show password"

                                // Toggle button to hide or display password
                                IconButton(onClick = {passwordVisible = !passwordVisible}){
                                    Icon(imageVector  = image, description, tint = Color.Black)
                                }
                            })
//                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "REGISTER", fontSize = 20.sp)
                }

            }

        }
    }

}