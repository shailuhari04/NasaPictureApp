package com.droidplusplus.nasapictureapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidplusplus.nasapictureapp.data.model.DataItem
import com.droidplusplus.nasapictureapp.data.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository) : ViewModel() {

    // MutableLiveData for post the data result value
    private val _mDataResultLiveData = MutableLiveData<List<DataItem>>()

    // LiveData for Observe the value
    val mDataResultLiveData: LiveData<List<DataItem>> = _mDataResultLiveData


    /**
     * suspend fun which gets data from the repository and post value of LiveData<List<DataItem>>.
     */
    suspend fun getDataResult() {
        viewModelScope.launch {
            val result = repository.getDataResult()
            _mDataResultLiveData.postValue(result)
        }
    }
}