package com.pam.wibulist.State

data class SignUpState(
    val isloading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)

