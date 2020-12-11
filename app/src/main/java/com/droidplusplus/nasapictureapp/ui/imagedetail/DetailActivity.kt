package com.droidplusplus.nasapictureapp.ui.imagedetail

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.nasapictureapp.R
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
        // Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.action_day_night_mode -> {
                // Get new mode.
                val mode =
                    if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
                        Configuration.UI_MODE_NIGHT_NO
                    ) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    }

                // Change UI Mode
                AppCompatDelegate.setDefaultNightMode(mode)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}