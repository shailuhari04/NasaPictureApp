package com.droidplusplus.nasapictureapp.data.repository

import com.droidplusplus.nasapictureapp.data.model.DataItem

interface DataRepository {
    suspend fun getDataResult(): List<DataItem>?
}