package com.droidplusplus.nasapictureapp.utils

import com.droidplusplus.nasapictureapp.data.model.DataItem
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

/**
 * Reads any InputStream and returns it in String.
 * @return The text in InputStream as String or null if something went wrong
 */
suspend fun InputStream.readAsString(): String? {
    return try {
        val availableBytes = ByteArray(available())
        read(availableBytes, 0, availableBytes.size)
        String(availableBytes)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}

/**
 * @param str
 * Converts only string to List of DataItem
 * @return The List of DataItem
 */
suspend fun stringToList(str: String?): List<DataItem> {
    return Gson().fromJson(str, Array<DataItem>::class.java).toList()
}