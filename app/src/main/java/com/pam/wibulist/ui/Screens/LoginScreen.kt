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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.R
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.buttonColor
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

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.backgroundColor),

        ) {
        Column() {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.group_27_removebg_preview),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp),
                    alignment = Alignment.TopEnd,
                )
            }
            Text(
                text = stringResource(R.string.welcome),
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
            Text(
                text = stringResource(R.string.back),
                fontSize = 32.sp,
                color = MaterialTheme.colors.buttonColor,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 2.dp, bottom = 50.dp)
            )
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
                    .fillMaxWidth()
                    .height(1000.dp)
                    .background(Color.White)
                    .padding(20.dp),
            ) {
                //TextField
                OutlinedTextField(
                    value = emailInput.toString(),
                    onValueChange = {emailInput = it},
                    label = { Text(text = stringResource(R.string.Email))},
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
                    value = passwordInput.toString(),
                    onValueChange = {passwordInput = it},
                    label = { Text(text = stringResource(R.string.Password))},
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

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate(route = Screens.SignUpScreen.route)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.dnthvacc),
                            fontWeight = FontWeight.Thin,
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = {
                            navController.navigate(route = Screens.ChangePasswordScreen.route)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.forgot_password),
                            fontWeight = FontWeight.Thin,
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    }
                }



                //Button Login
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.loginUser(emailInput,passwordInput)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(51, 133, 255,),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = CutCornerShape(10)
                ) {
                    Text(
                        text = stringResource(R.string.Login),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
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
                            val loginData = loginData(email = emailInput)
                            sharedViewModel.addPerson(loginData)
                            Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                            navController.navigate(route = Screens.bottomNavGr.route)
                        }
                    }
                }

                //JIKA GAGAL
                LaunchedEffect(key1 = state.value?.isError)
                {
                    scope.launch {
                        if (state.value?.isError?.isNotEmpty()==true)
                        {
                            val error = state.value?.isError
                            Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

}