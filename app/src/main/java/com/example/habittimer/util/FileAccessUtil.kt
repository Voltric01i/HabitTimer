package com.example.habittimer.util

import android.content.Context
import java.io.FileOutputStream
import java.io.IOException
import java.lang.StringBuilder


object FileAccessUtil {
    fun saveFile(context: Context,fileName: String, str: String){
        try {
            val outputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(str.toByteArray())
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    fun readFile(context: Context,fileName: String): String?{
        val stringBuilder = StringBuilder()
        try {
            val inputStream = context.openFileInput(fileName)
            val reader = inputStream.bufferedReader()
            for (lineBuffer in reader.readLines()) {
                stringBuilder.append(lineBuffer + "\n")
            }
            reader.close()
            inputStream.close()
            return stringBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

}