package com.example.androidkotlinworkshop

sealed class Todo(val viewType: Int) {

    companion object {
        const val TYPE_TILE = 0
        const val TYPE_ITEM = 1
    }

    data class Title(val text: String) : Todo(TYPE_TILE)

    data class Item(val memo: String, val checked: Boolean) : Todo(TYPE_ITEM)

}