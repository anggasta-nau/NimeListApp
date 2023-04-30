package com.pam.wibulist.ui.Screens

import com.pam.wibulist.models.AnimeTrendViewModel
import com.pam.wibulist.ui.data.storedUsename
import com.pam.wibulist.R
import android.util.Log
import android.widget.Space
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.buttonColor
import com.pam.wibulist.viewModel.sharedViewModel


@Composable
fun ProfileScreen(
    avm: AnimeTrendViewModel,
    sharedViewModel: sharedViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val dataStore = storedUsename(context)

    var name by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val setUserInformation = sharedViewModel.person
    val user = Firebase.auth.currentUser!!
    var usernameInput by remember { mutableStateOf("") }

    sharedViewModel.retrieveData(
        email = setUserInformation!!.email,
        context = context
    ) { data ->
        name = data.name
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//            Text(
//                text = "Profile",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Color.White,
//                modifier = Modifier.padding(top = 20.dp)
//            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(painter = painterResource(
                id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Edit Profile",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White,
                )
            }
        Column(modifier = Modifier.padding(20.dp)) {
            //Text
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Username",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = usernameInput.toString(),
                onValueChange ={usernameInput = it},
                label = {
                    Text(
                        text = "$name",
                        fontSize = 14.sp,
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
                text = "Email",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = usernameInput.toString(),
                onValueChange ={usernameInput = it},
                enabled = false,
                label = {
                    Text(
                        //Tampilkan email
                        text = "isi email",
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Thin
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(51, 133, 255,),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Log Out",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(51, 133, 255,),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(start = 130.dp)
                ) {
                    Text(
                        text = "Save Profile",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
    }

}


