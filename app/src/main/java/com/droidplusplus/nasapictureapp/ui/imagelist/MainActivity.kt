package com.droidplusplus.nasapictureapp.ui.imagelist

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.nasapictureapp.R
import com.droidplusplus.nasapictureapp.base.BaseActivity
import com.droidplusplus.nasapictureapp.data.repository.DataRepositoryImpl
import com.droidplusplus.nasapictureapp.databinding.ActivityMainBinding
import com.droidplusplus.nasapictureapp.ui.MainViewModel
import com.droidplusplus.nasapictureapp.ui.MainViewModelFactory
import com.droidplusplus.nasapictureapp.ui.imagedetail.DetailActivity
import com.droidplusplus.nasapictureapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        // RecyclerView adapter setUp
        mViewBinding.imagesListRV.adapter = mAdapter

        // Adapter listener initialize
        mAdapter.itemClickListener = { position ->
            val detailScreenIntent = Intent(this, DetailActivity::class.java)
            detailScreenIntent.putExtra(Constants.ITEM_POSITION, position)
            startActivity(detailScreenIntent)
        }

        // Observe data from ViewModel
        mViewModel.mDataResultLiveData.observe(this, { result ->
            result?.takeIf { it.isNotEmpty() }?.let {
                mAdapter.submitList(result)
            }
        })
    }

    override fun getViewBing(): ActivityMainBinding = mViewBinding

    override fun getViewModel(): MainViewModel = mViewModel

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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