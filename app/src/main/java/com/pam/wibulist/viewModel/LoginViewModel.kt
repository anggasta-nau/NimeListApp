package com.pam.wibulist.viewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.NavigationGraph.NavigationGraph
import com.pam.wibulist.State.LoginState
import com.pam.wibulist.data.authRepository
import com.pam.wibulist.ui.theme.WibuListTheme
import com.pam.wibulist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: authRepository
): ViewModel(){

    val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()


    fun loginUser(email:String, password:String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{result->
            when(result)
            {
                is Resource.Success ->{
                    _loginState.send(LoginState(isSuccess = "Login Success!"))
                }
                is Resource.loading ->{
                    _loginState.send(LoginState(isloading = true))
                }
                is Resource.error ->{
                    _loginState.send(LoginState(isError = result.message))
                }
            }
        }
    }



}