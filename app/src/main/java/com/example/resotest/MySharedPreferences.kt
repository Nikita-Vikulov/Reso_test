package com.example.resotest

import android.content.Context
import android.content.SharedPreferences
import com.example.resotest.model.Subject
import com.google.gson.Gson

class MySharedPreferences(context: Context) {
    companion object {
        private const val PREF_NAME = "MyPrefs"
        private const val SUBJECT_KEY = "subject_key"
    }

    private val sharedPreferences: SharedPreferences? =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val gson: Gson = Gson()

    fun saveSubject(subject: Subject) {
        val subjectJson = gson.toJson(subject)
        sharedPreferences?.edit()?.putString(SUBJECT_KEY, subjectJson)?.apply()
    }

    fun getSubject(): Subject? {
        val subjectJson = sharedPreferences?.getString(SUBJECT_KEY, null)
        return if (subjectJson != null) {
            gson.fromJson(subjectJson, Subject::class.java)
        } else {
            null
        }
    }
}