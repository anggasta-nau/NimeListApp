package com.pam.wibulist.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pam.wibulist.utils.loginData
import com.pam.wibulist.utils.userData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class sharedViewModel (): ViewModel() {

    fun saveData(
        userData: userData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("User")
            .document(userData.email)

        try {
            firestoreRef.set(userData).addOnSuccessListener {
                Toast.makeText(context, "hehe", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception)
        {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        email: String,
        context: Context,
        data: (userData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("User")
            .document(email)

        try {
            firestoreRef.get().addOnSuccessListener {
                if(it.exists())
                {
                    val UserData = it.toObject<userData>()!!
                    data(UserData)
                }

            }
        } catch (e: Exception)
        {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    var person by mutableStateOf<loginData?>(null)
        private set
    fun addPerson(loginData: loginData)
    {
        person = loginData
    }
}