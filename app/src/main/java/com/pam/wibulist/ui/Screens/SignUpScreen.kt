package com.pam.wibulist.ui.Screens

import android.content.Intent
import android.service.autofill.OnClickAction
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
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
import com.pam.wibulist.R
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.buttonColor
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

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.backgroundColor),
    ) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.group_27_removebg_preview),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                    .align(Alignment.End)
                    .width(150.dp)
                    .height(100.dp)
            )
            Text(

                text = stringResource(R.string.create),

                text = stringResource(R.string.change_your),

                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
            Text(

                text = stringResource(R.string.accnt),

                text = stringResource(R.string.account),

                fontSize = 32.sp,
                color = MaterialTheme.colors.buttonColor,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 2.dp, bottom = 50.dp)
            )
            Column(modifier = Modifier
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
                    value = Username.toString(),
                    onValueChange = {Username = it},
                    label = { Text(text = stringResource(R.string.username))},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.buttonColor,
                        unfocusedBorderColor = Color.Black.copy(0.7f),
                        focusedLabelColor = MaterialTheme.colors.buttonColor
                    ),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
                OutlinedTextField(
                    value = emailInput.toString(),
                    onValueChange = {emailInput = it},
                    label = { Text(text = stringResource(R.string.Email))},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.buttonColor,
                        unfocusedBorderColor = Color.Black.copy(0.7f),
                        focusedLabelColor = MaterialTheme.colors.buttonColor
                    ),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
                OutlinedTextField(
                    value = passwordInput.toString(),
                    onValueChange = {passwordInput = it},
                    label = { Text(text = stringResource(R.string.Password))},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.buttonColor,
                        unfocusedBorderColor = Color.Black.copy(0.7f),
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
                    onValueChange = {confirmPassword = it},
                    label = { Text(text = stringResource(R.string.CPaswoord))},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.buttonColor,
                        unfocusedBorderColor = Color.Black.copy(0.7f),
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
                        text = stringResource(R.string.Login),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(
                    onClick = {
                        //getEmailFromSignUpForm.launch("")
                        navController.navigate(route = Screens.LoginScreen.route)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.alrdyhvacc),
                        fontWeight = FontWeight.Thin,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }

                //LOADING
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (state.value?.isloading == true)
                    {
                        CircularProgressIndicator()
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
}


//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview() {
//    SignUpScreen()
//}