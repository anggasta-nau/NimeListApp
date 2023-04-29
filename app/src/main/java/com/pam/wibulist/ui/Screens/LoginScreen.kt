package com.pam.wibulist.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.utils.loginData
import com.pam.wibulist.viewModel.LoginViewModel
import com.pam.wibulist.viewModel.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    sharedViewModel: sharedViewModel,
    navController: NavHostController = rememberNavController()
) {
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.loginState.collectAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .padding(top = 250.dp)
        ) {
            Text(
                text = "Hi, Welcome Back",
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,top = 319.dp)
        ) {
            OutlinedTextField(
                value = emailInput.toString(),
                onValueChange = { emailInput = it },
                label = { Text(text = "Enter Your Email") },
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
        }

        Column(modifier = Modifier
            .padding(top = 450.dp)) {

            TextButton(onClick = {
                navController.navigate(route = Screens.ChangePasswordScreen.route)
            }
            ) {
                Text(text = "Forgot Password?",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(20.dp),)
            }
            //TOMBOL LOGIN
            Button(
                onClick = {
                    scope.launch {
                        viewModel.loginUser(emailInput,passwordInput)
                    } },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(14, 100, 210),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = CutCornerShape(10)
            ) {
                Text(
                    text = "Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
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
            modifier = Modifier.padding(20.dp,top = 750.dp)
        ) {
            //JIKA SUDAH PUNYA AKUN
            TextButton(onClick = {
                navController.navigate(route = Screens.SignUpScreen.route)
            }) {
                Text(text = "Don’t have an account ? Sign Up", color = Color.Black)
            }
        }
    }
    //JIKA SUKSES
    LaunchedEffect(key1 = state.value?.isSuccess)
    {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty()==true)
            {
                val success = state.value?.isSuccess
                val loginData = loginData(email = emailInput)
                sharedViewModel.addPerson(loginData)
                Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                navController.navigate(route = Screens.bottomNavGr.route)
            }
        }
    }

}