package com.pam.wibulist.ui.Screens

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import com.pam.wibulist.models.AnimeTrendViewModel
import com.pam.wibulist.ui.data.storedUsename
import com.pam.wibulist.R
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pam.wibulist.MainActivity
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.buttonColor
import com.pam.wibulist.utils.loginData
import com.pam.wibulist.viewModel.sharedViewModel


@Composable
fun ProfileScreen(
    avm: AnimeTrendViewModel,
    sharedViewModel: sharedViewModel,
    navController: NavController,
    onSubmitActionEvent: (img: ImageBitmap, context: Context, emailpic: String) -> Unit
) {
    val context = LocalContext.current
    val dataStore = storedUsename(context)

    var takenImage by remember {
        mutableStateOf(
            BitmapFactory.decodeResource(context.resources, R.drawable.no_image_available_svg).asImageBitmap()
        )
    }

    val takePictureContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { _takenImageBitmap ->
            takenImage = _takenImageBitmap!!.asImageBitmap()
        }
    )

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val setUserInformation = sharedViewModel.person
    val user1 = FirebaseAuth.getInstance()
    var emailInput by remember { mutableStateOf("") }
    var usernameInput by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    sharedViewModel.retrieveData(
        email = setUserInformation!!.email,
        context = context
    ) { data ->
        name = data.name
        email = data.email
    }


    Column(
        modifier = Modifier
            .height(1950.dp)
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.log_out),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.End)
                .clickable {
                    user1.signOut()
                    context.startActivity(Intent(context, MainActivity::class.java))
                }

        )

        Spacer(modifier = Modifier.height(20.dp))
        Image(
            bitmap = takenImage,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .width(150.dp)
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            onClick = { takePictureContract.launch() }
        ) {
            Text(
                text = stringResource(R.string.edit_photo_profile),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.White,
            )
        }
        Column(modifier = Modifier.padding(20.dp)) {
            //Text
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(R.string.username),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = usernameInput.toString() ,
                onValueChange ={usernameInput = it},
                enabled = false,
                label = {
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Thin,
                    )
                },

                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.White)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                //Tampilkan nama bolh
                text = stringResource(R.string.Email),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = emailInput.toString() ,
                onValueChange ={emailInput = it},
                enabled = false,
                label = {
                    Text(
                        text = email,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Thin,
                    )
                },

                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.White)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                //password
                text = stringResource(R.string.password),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = password,
                onValueChange ={password = it},
                label = {
                    Text(
                        //Tampilkan Password
                        text = stringResource(R.string.Password),
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Thin
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.White)
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                //password
                text = stringResource(R.string.confirm_password),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = confirmPassword,
                onValueChange ={confirmPassword = it},
                label = {
                    Text(
                        //Tampilkan Password
                        text = stringResource(R.string.confirm_password),
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Thin
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.White)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween) {

                Button(
                    onClick = {
                        if(password.equals(confirmPassword))
                        {
                            if (password.length < 6)
                            {
                                Toast.makeText(context, context.getString(R.string.make_sure_your_password_lenght_more_than_6), Toast.LENGTH_LONG).show()
                            }
                            else
                            {
                                val user = Firebase.auth.currentUser!!
                                user.updatePassword(password)
                                user1.signOut()
                                context.startActivity(Intent(context, MainActivity::class.java))
                                Toast.makeText(context, context.getString(R.string.password_has_been_changed_please_login_again), Toast.LENGTH_LONG).show()
                            }
                        }
                        else
                        {
                            Toast.makeText(context, context.getString(R.string.make_sure_your_password_and_your_confirm_password_match), Toast.LENGTH_LONG).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(51, 133, 255,),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(R.string.save_password),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                Button(
                    onClick = {
                        onSubmitActionEvent(takenImage, context, emailInput)
                        takenImage = BitmapFactory.decodeResource(context.resources, R.drawable.no_image_available_svg).asImageBitmap()

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(51, 133, 255,),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(start = 80.dp)
                ) {
                    Text(
                        text = stringResource(R.string.save_profile),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
        Spacer(modifier = Modifier.height(50.dp))
    }

}


