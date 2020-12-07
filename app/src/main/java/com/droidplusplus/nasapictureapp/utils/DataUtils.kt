package com.droidplusplus.nasapictureapp.utils

import java.io.IOException
import java.io.InputStream

/**
 * Reads any InputStream and returns it in String.
 * @return The text in InputStream as String or null if something went wrong
 */
suspend fun InputStream.readAsString(): String? {
    try {
        val availableBytes = ByteArray(available())
        read(availableBytes, 0, availableBytes.size)
        return String(availableBytes)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
}