package com.example.gmergames.dataStore

data class UserPreferences (
    val name : String = "",
    val showCheckBox : Boolean = true
){
    companion object {
        const val SETTINGS_FILE = "settings"
        const val USER_NAME = "username"
        const val SHOW_CHECKBOX = true
    }

}