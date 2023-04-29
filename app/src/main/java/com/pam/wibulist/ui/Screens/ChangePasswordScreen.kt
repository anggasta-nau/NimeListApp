package com.pam.wibulist.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.viewModel.sharedViewModel
import kotlinx.coroutines.launch


@Composable
fun ChangePasswordScreen(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: sharedViewModel,
)
{
    var email by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable{ mutableStateOf("") }
    var name by rememberSaveable{ mutableStateOf("") }
    var confirmPassword by rememberSaveable{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val setUserInformation = sharedViewModel.person


    val user = Firebase.auth.currentUser!!

    if (user != null)
    {
        sharedViewModel.retrieveData(
            email = setUserInformation!!.email,
            context = context
        ){ data ->
            name = data.name
        }

        //JIKA USER SIGN IN
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "$name", textAlign = TextAlign.Center, fontSize = 40.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))



            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = password, onValueChange = {
                password = it
            }, modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                    Text(text = "Password")
                }

            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = confirmPassword, onValueChange = {
                confirmPassword = it
            }, modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                    Text(text = "Confirm Password")
                }

            )

            //BUTTON SUBMIT
            Button(
                onClick = {
                    if(password.equals(confirmPassword))
                    {
                        scope.launch {
                            user.updatePassword(password)
                            navController.navigate(route = Screens.LoginScreen.route)
                            Toast.makeText(context, "Password has been change, please Login again!!", Toast.LENGTH_LONG).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "Make sure your Password and your Confirm Password match", Toast.LENGTH_LONG).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Submit", color = Color.White, modifier = Modifier.padding(7.dp))

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
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

        }

    }
}