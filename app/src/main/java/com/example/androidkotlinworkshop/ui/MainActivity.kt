package com.example.androidkotlinworkshop.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidkotlinworkshop.R
import com.example.androidkotlinworkshop.RvAdapter.ToDoAdapter
import com.example.androidkotlinworkshop.databinding.ActivityMainBinding
import com.example.androidkotlinworkshop.resManager.AppResManager
import com.example.androidkotlinworkshop.viewModel.ToDoViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mViewModel by lazy { ViewModelProvider(this, MyViewModelFactory(this)).get<ToDoViewModel>() }

    private val mViewModel2 by lazy { ToDoViewModel(AppResManager(this)) }
    private val mLifecycleOwner = MyLifeCycleOwner()

    private var mTodoAdapter = ToDoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView()
        lifeCycleTest()
    }

    override fun onResume() {
        super.onResume()
        Log.d("androidTest","test")
    }

    private fun initView() {
        mBinding.mRvDemo.adapter = mTodoAdapter
        mBinding.mRvDemo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.mRvDemo.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

//        mListToDos = mListToDos.toMutableList().apply { add(Todo.Title("備忘錄")) }
//        mTodoAdapter.submitList(mListToDos)

        //一旦發現值有任何的變化時 都可以監聽到
        mViewModel.mListToDos.observe(this, Observer { listToDos ->
            mTodoAdapter.submitList(listToDos)
        })

        mBinding.mBtnAdd.setOnClickListener(this)
        mBinding.mBtnAdd2.setOnClickListener(this)
    }



    private fun lifeCycleTest() {
        mViewModel2.mListToDos.observe(mLifecycleOwner, Observer { listToDos ->
            Log.d("androidTest", "$listToDos")
        })

//        mLifecycleOwner.start()
//        mLifecycleOwner.destory()

        Log.d("androidTest","end of test")

        //mViewModel2.addItemIntent.postValue(Unit)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mBtnAdd -> {
                //mListToDos = mListToDos.toMutableList().apply { add(Todo.Item("world", false)) }
                //mViewModel.addItem("item")
                //mTodoAdapter.submitList(mViewModel.mListToDos)
                mViewModel.addItemIntent.postValue(Unit)
            }

            R.id.mBtnAdd2 -> {
                mViewModel.addItemIntent2.postValue("item")
               // mViewModel2.addItemIntent.postValue(Unit)
            }
        }
    }

    class MyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when (modelClass.name) {
                ToDoViewModel::class.java.name -> {
                    ToDoViewModel(AppResManager(context)) as T
                }
                else -> throw IllegalAccessError()
            }
        }
    }

    /**
     * 可隨意操作生命週期
     */
    class MyLifeCycleOwner : LifecycleOwner {

        private val mLifecycleRegistry = LifecycleRegistry(this)

        init {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }

        fun start() {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        }

        fun onRusme(){
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        fun destory(){
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        }

        override fun getLifecycle(): Lifecycle {
            return mLifecycleRegistry
        }
    }
}

