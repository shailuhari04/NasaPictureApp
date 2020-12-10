package com.droidplusplus.nasapictureapp.ui.imagedetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.nasapictureapp.base.BaseActivity
import com.droidplusplus.nasapictureapp.data.repository.DataRepositoryImpl
import com.droidplusplus.nasapictureapp.databinding.ActivityDetailBinding
import com.droidplusplus.nasapictureapp.ui.MainViewModel
import com.droidplusplus.nasapictureapp.ui.MainViewModelFactory

class DetailActivity : BaseActivity<ActivityDetailBinding, MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mActivityViewBinding.root)
    }

    override fun getViewBing(): ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

    override fun getViewModel(): MainViewModel =
        ViewModelProvider(this, MainViewModelFactory(DataRepositoryImpl(this))).get(MainViewModel::class.java)
}