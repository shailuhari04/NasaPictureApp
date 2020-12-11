package com.droidplusplus.nasapictureapp.ui

import android.content.Context
import com.droidplusplus.nasapictureapp.base.BaseViewModelTest
import com.droidplusplus.nasapictureapp.data.repository.DataRepository
import com.droidplusplus.nasapictureapp.data.repository.DataRepositoryImpl
import com.droidplusplus.nasapictureapp.datasource.createDataItemListResult
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Test

class MainActivityViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: MainViewModel

    private lateinit var mRepository: DataRepository

    private val mContext: Context = mock()

    override fun setup() {
        super.setup()
        mRepository = DataRepositoryImpl(mContext)
        viewModel = MainViewModel(mRepository)
    }

    @Test
    fun `get data result`() {
        val fakeData = createDataItemListResult()
        Assert.assertEquals(4, fakeData.size)
        Assert.assertEquals("Starburst Galaxy M94 from Hubble", fakeData[0].title)
    }

    @Test
    fun `load data result`() {
        viewModel.mDataResultLiveData.observeForever { dataList ->
            Assert.assertNotNull(dataList)
            Assert.assertEquals(4, dataList.size)
        }

        testCoroutineRule.runBlockingTest {
            val fakeData = createDataItemListResult()
            viewModel.postResult(fakeData)
        }
    }

}