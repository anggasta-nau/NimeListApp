package com.pam.wibulist.ui.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class storedUsename (private val context: Context){

    companion object{
        private val Context.data: DataStore<Preferences> by preferencesDataStore("FirstLastName")
        val FIRSTNAME_KEY = stringPreferencesKey("Fist_Name")
        val LASTNAME_KEY = stringPreferencesKey("Last_Name")
    }

    val getFirstName: Flow<String?> = context.data.data
        .map { preferences -> preferences[FIRSTNAME_KEY] ?:"" }

    val getLastName: Flow<String?> = context.data.data
        .map { preferences -> preferences[LASTNAME_KEY] ?:"" }


    suspend fun saveFirstName(firstName: String)
    {
        context.data.edit{ preferences -> preferences[FIRSTNAME_KEY ] = firstName}
    }

    suspend fun saveLastName(lastName: String)
    {
        context.data.edit{ preferences -> preferences[LASTNAME_KEY ] = lastName}
    }
}