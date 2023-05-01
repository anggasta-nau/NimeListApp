package com.pam.wibulist.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.R
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.buttonColor
import com.pam.wibulist.viewModel.sharedViewModel
import kotlinx.coroutines.launch


@Composable
fun ChangePasswordScreen(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: sharedViewModel,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val setUserInformation = sharedViewModel.person
    val user = Firebase.auth.currentUser!!

//    if (user != null) {
//        sharedViewModel.retrieveData(
//            email = setUserInformation!!.email,
//            context = context
//        ) { data ->
//            name = data.name
//        }

        //JIKA USER Lupa Password
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.backgroundColor),
        ) {
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.group_27_removebg_preview),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp)
                )
                Text(
                    text = "Welcome",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
                )
                Text(
                    text = "Back",
                    fontSize = 32.sp,
                    color = MaterialTheme.colors.buttonColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 2.dp,
                        bottom = 50.dp
                    )
                )
                Column(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                        )
                        .fillMaxWidth()
                        .height(700.dp)
                        .background(Color.White)
                        .padding(20.dp)
                ) {
                    //TextField
                    OutlinedTextField(
                        value = email.toString(),
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.buttonColor,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = MaterialTheme.colors.buttonColor
                        ),
                        shape = RoundedCornerShape(20.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                    OutlinedTextField(
                        value = password.toString(),
                        onValueChange = { password = it },
                        label = { Text(text = "Password") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.buttonColor,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = MaterialTheme.colors.buttonColor
                        ),
                        shape = RoundedCornerShape(20.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                    OutlinedTextField(
                        value = confirmPassword.toString(),
                        onValueChange = { confirmPassword = it },
                        label = { Text(text = "Confirm Password") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.buttonColor,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = MaterialTheme.colors.buttonColor
                        ),
                        shape = RoundedCornerShape(20.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )

                    //Button Login
                    Button(
                        onClick = {
                            if (password.equals(confirmPassword)) {
                                scope.launch {
                                    user.updatePassword(password)
                                    navController.navigate(route = Screens.LoginScreen.route)
                                    Toast.makeText(
                                        context,
                                        "Password has been change, please Login again!!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Make sure your Password and your Confirm Password match",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(51, 133, 255,),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        shape = CutCornerShape(10)
                    ) {
                        Text(
                            text = "Submit",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                    //KE HALAMAN LOGIN
                    TextButton(
                        onClick = {
                            //getEmailFromSignUpForm.launch("")
                            navController.navigate(route = Screens.LoginScreen.route)
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(
                            text = "Already have account? Sign in",
                            fontWeight = FontWeight.Thin,
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                }
            }

        }
//    }
}
