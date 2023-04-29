package com.pam.wibulist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.pam.wibulist.utils.Resource


class authRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): authRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow<Resource<AuthResult>> {
            emit(Resource.loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        } .catch {
            emit(Resource.error(it.message.toString()))
        }
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow<Resource<AuthResult>> {
            emit(Resource.loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        } .catch {
            emit(Resource.error(it.message.toString()))
        }
    }
}