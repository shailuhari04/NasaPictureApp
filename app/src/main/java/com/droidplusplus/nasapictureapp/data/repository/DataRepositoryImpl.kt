package com.droidplusplus.nasapictureapp.data.repository

import android.content.Context
import com.droidplusplus.nasapictureapp.data.model.DataItem
import com.droidplusplus.nasapictureapp.utils.readAsString
import com.droidplusplus.nasapictureapp.utils.stringToList
import java.io.InputStream

class DataRepositoryImpl(private val context: Context) : DataRepository {

    override suspend fun getDataResult(): List<DataItem>? {
        val inputStream: InputStream = context.assets.open("data.json")

        return inputStream.readAsString()?.let {
            stringToList(it)
        } ?: run { null }
    }
}