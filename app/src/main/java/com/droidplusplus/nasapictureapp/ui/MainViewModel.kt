package com.droidplusplus.nasapictureapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidplusplus.nasapictureapp.data.model.DataItem
import com.droidplusplus.nasapictureapp.data.repository.DataRepository

class MainViewModel(private val repository: DataRepository) : ViewModel() {

    // MutableLiveData for post the data result value
    private val _mDataResultLiveData = MutableLiveData<List<DataItem>>()

    // LiveData for Observe the value
    val mDataResultLiveData: LiveData<List<DataItem>> = _mDataResultLiveData
}