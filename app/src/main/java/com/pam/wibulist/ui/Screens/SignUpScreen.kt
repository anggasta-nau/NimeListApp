package com.pam.wibulist.ui.Screens

import android.content.Intent
import android.service.autofill.OnClickAction
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.dataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.utils.userData
import com.pam.wibulist.viewModel.SignUpViewModel
import com.pam.wibulist.viewModel.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen (
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    sharedViewModel: sharedViewModel
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModel.signupState.collectAsState(initial = null)
    var Username by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 59.dp)
        ) {
            Text(
                text = "Create an account",
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        Column{
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 89.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Connect with your fiends today!",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.LightGray,
                )
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 149.dp)){
            OutlinedTextField(
                value = Username.toString(),
                onValueChange = { Username = it},
                label = { Text(text = "Enter Your Username" ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(29, 196, 206),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(29, 196, 206)
                ),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = emailInput.toString(),
                onValueChange = { emailInput = it },
                label = { Text(text = "Enter Your Email")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(29, 196, 206),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(29, 196, 206)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = passwordInput.toString(),
                onValueChange = { passwordInput = it },
                label = { Text(text = "Enter Your Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(29, 196, 206),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(29, 196, 206)
                ),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = confirmPassword.toString(),
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Your Passowrd") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(29, 196, 206),
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(29, 196, 206)
                ),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            )

            //TOMBOL SIGNUP
            Button(
                onClick = {
                    if(passwordInput.equals(confirmPassword))
                    {
                        scope.launch {
                            viewModel.registeruser(emailInput,passwordInput)
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "Make sure your Password and your Confirm Password match", Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(14, 100, 210),
                    contentColor = Color.White
                ),
                shape = CutCornerShape(10)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            //LOADING
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (state.value?.isloading == true)
                {
                    CircularProgressIndicator()
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 200.dp)
            ) {
                //JIKA SUDAH PUNYA AKUN
                TextButton(onClick = {
                    navController.navigate(route = Screens.LoginScreen.route)
                }) {
                    Text(text = "Already have an account? Login", color = Color.Black)
                }
            }

            //JIKA SUKSES
            LaunchedEffect(key1 = state.value?.isSuccess)
            {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty()==true)
                    {
                        val success = state.value?.isSuccess
                        val userData = userData(
                            email = emailInput,
                            name = Username
                        )
                        Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                        sharedViewModel.saveData(userData = userData, context = context)
                        navController.navigate(route = Screens.LoginScreen.route)
                    }
                }
            }
        }


    }
}


//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview() {
//    SignUpScreen()
//}