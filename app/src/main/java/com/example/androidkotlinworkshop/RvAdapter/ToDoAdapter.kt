package com.example.androidkotlinworkshop.RvAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinworkshop.R
import com.example.androidkotlinworkshop.data.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter : ListAdapter<Todo, RecyclerView.ViewHolder>(

    object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.viewType == newItem.viewType
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            Todo.TYPE_TILE -> TodoTitleViewHolder(parent)
            else -> TodoItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val todo = getItem(position)){
            is Todo.Title -> (holder as TodoTitleViewHolder).bind(todo)
            is Todo.Item -> (holder as TodoItemViewHolder).bind(todo)
        }
    }

    class TodoItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)) {
        private val mChkToDo: AppCompatCheckBox = itemView.mChkToDo
        fun bind(todo: Todo.Item){
            mChkToDo.text = todo.memo
            mChkToDo.isChecked = todo.checked
        }
    }

    class TodoTitleViewHolder(parent: ViewGroup) :RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_title,parent,false)){
        fun bind(todo: Todo.Title){
            (itemView as AppCompatTextView).text = todo.text
        }
    }


}