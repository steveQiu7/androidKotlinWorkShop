package com.example.androidkotlinworkshop.resManager

import android.content.Context
import com.example.androidkotlinworkshop.R

class AppResManager(private val context: Context) :ResManagerInterface {
    override fun getTodoTitle(): String {
        return context.getString(R.string.todo_list_title)
    }
}