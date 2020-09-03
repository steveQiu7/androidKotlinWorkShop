package com.example.androidkotlinworkshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidkotlinworkshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var mListToDos = listOf<Todo>()
    private var mTodoAdapter = ToDoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        mBinding.mRvDemo.adapter = mTodoAdapter
        mBinding.mRvDemo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.mRvDemo.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        mListToDos = mListToDos.toMutableList().apply { add(Todo.Title("備忘錄")) }
        mTodoAdapter.submitList(mListToDos)

        mBinding.mBtnAdd.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mBtnAdd -> {
                mListToDos = mListToDos.toMutableList().apply { add(Todo.Item("world", false)) }
                mTodoAdapter.submitList(mListToDos)
            }
        }
    }

}