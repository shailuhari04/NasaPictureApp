package com.droidplusplus.nasapictureapp.ui.imagelist

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.nasapictureapp.base.BaseActivity
import com.droidplusplus.nasapictureapp.data.repository.DataRepositoryImpl
import com.droidplusplus.nasapictureapp.databinding.ActivityMainBinding
import com.droidplusplus.nasapictureapp.ui.MainViewModel
import com.droidplusplus.nasapictureapp.ui.MainViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    // Delegates
    private val mViewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(DataRepositoryImpl(this))).get(
            MainViewModel::class.java
        )
    }

    private val mAdapter by lazy { ImageListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mActivityViewBinding.root)
    }

    override fun getViewBing(): ActivityMainBinding = mViewBinding

    override fun getViewModel(): MainViewModel = mViewModel
}