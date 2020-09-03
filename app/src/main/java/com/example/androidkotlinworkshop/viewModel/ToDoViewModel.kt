package com.example.androidkotlinworkshop.viewModel

import androidx.lifecycle.*
import com.example.androidkotlinworkshop.data.Todo
import com.example.androidkotlinworkshop.resManager.ResManagerInterface

class ToDoViewModel(private val resManagerInterface: ResManagerInterface) : ViewModel() {

    //var mListToDos: MutableLiveData<List<Todo>> = MutableLiveData(mutableListOf<Todo>(Todo.Title("備忘錄"), Todo.Title("第二的標題")))

    var mCount = 0

    val addItemIntent = MutableLiveData<Unit>()
    val addItemIntent2 = MutableLiveData<String>()

    var mListToDos: LiveData<List<Todo>> = MediatorLiveData<List<Todo>>().apply {

        value = mutableListOf<Todo>(Todo.Title(resManagerInterface.getTodoTitle()))

        val observer = Observer<Unit>{
            val newList: List<Todo> = value!!.toMutableList().apply {
                add(Todo.Item("List - $mCount", false))
            }
            value = newList
            mCount++
        }

        val observer2 = Observer<String> {
            val newList:List<Todo> = value!!.toMutableList().apply {
                add(Todo.Item("$it - $mCount",false))
            }
            value = newList
            mCount++
        }

        addSource(addItemIntent,observer)
        addSource(addItemIntent2,observer2)
    }


//    fun addItem(name: String) {
//        mListToDos = mListToDos.toMutableList().apply {
//            add(Todo.Item("$name - $mCount", false))
//        }
//        val newList: List<Todo> = mListToDos.value?.toMutableList()?.apply { add(Todo.Item("$name - $mCount", false)) } ?: listOf()
//        mListToDos.postValue(newList)
//        mCount++
//    }

}