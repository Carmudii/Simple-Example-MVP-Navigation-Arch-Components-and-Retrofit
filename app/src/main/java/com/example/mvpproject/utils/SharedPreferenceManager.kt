package com.example.mvpproject.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager @SuppressLint("CommitPrefEdits") constructor (context: Context?) {

    companion object {

        val INIT_PREFERENCES = "user-storage"
        val TOKEN = "_TOKEN"

    }

    public lateinit var preferenceManager: SharedPreferences
    public lateinit var editor: SharedPreferences.Editor

    init {
        preferenceManager = context!!.getSharedPreferences(INIT_PREFERENCES, Context.MODE_PRIVATE)
        editor = preferenceManager.edit()
    }


}