package com.pam.wibulist.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val DarkPurple = Color(0xFF040112)
val Cyan = Color(0xFF3385FF)
val purple = Color(0xFF2E178C)

val Colors.backgroundColor
    get() = if (isLight) DarkPurple else Color.Black

val Colors.buttonColor
    get() = if (isLight) Cyan else Color.Blue

val Colors.gener
    get() = if (isLight) purple else Color.Blue