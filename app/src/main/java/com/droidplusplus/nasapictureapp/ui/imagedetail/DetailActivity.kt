package com.droidplusplus.nasapictureapp.ui.imagedetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.nasapictureapp.base.BaseActivity
import com.droidplusplus.nasapictureapp.data.repository.DataRepositoryImpl
import com.droidplusplus.nasapictureapp.databinding.ActivityDetailBinding
import com.droidplusplus.nasapictureapp.ui.MainViewModel
import com.droidplusplus.nasapictureapp.ui.MainViewModelFactory
import com.droidplusplus.nasapictureapp.utils.Constants
import com.droidplusplus.nasapictureapp.utils.DepthPageTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : BaseActivity<ActivityDetailBinding, MainViewModel>() {

    // Delegate properties
    private val mViewBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    private val mViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(DataRepositoryImpl(this))).get(
            MainViewModel::class.java
        )
    }

    private val mAdapter by lazy { ImageDetailListAdapter() }

    private val mPosition: Int by lazy { intent.getIntExtra(Constants.ITEM_POSITION, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mActivityViewBinding.root)

        initialSetUp()

        loadData()
    }

    private fun loadData() {
        // Load data from ViewModel gertResult suspend function
        GlobalScope.launch(Dispatchers.IO) {
            mViewModel.getDataResult()
        }
    }

    private fun initialSetUp() {
        // ViewPager2 adapter setUp
        mViewBinding.imageDetailVP.adapter = mAdapter

        mViewBinding.imageDetailVP.setPageTransformer(DepthPageTransformer())

        // Observe data from ViewModel
        mViewModel.mDataResultLiveData.observe(this, { result ->
            result?.takeIf { it.isNotEmpty() }?.let {
                mAdapter.submitList(result)
                // To avoid animate when viewPager2 selected at certain position for that set smooth scroll to false
                mViewBinding.imageDetailVP.setCurrentItem(mPosition, false)
            }
        })
    }

    override fun getViewBing(): ActivityDetailBinding = mViewBinding

    override fun getViewModel(): MainViewModel = mViewModel
}