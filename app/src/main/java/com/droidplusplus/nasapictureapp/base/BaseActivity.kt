package com.droidplusplus.nasapictureapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected val mActivityViewModel by lazy { getViewModel() }

    protected val mActivityViewBinding by lazy { getViewBing() }

    abstract fun getViewBing(): VB

    abstract fun getViewModel(): VM

}