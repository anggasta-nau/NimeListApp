package com.pam.wibulist.data

import kotlinx.coroutines.flow.Flow
import com.google.firebase.auth.AuthResult
import com.pam.wibulist.utils.Resource

interface authRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
}