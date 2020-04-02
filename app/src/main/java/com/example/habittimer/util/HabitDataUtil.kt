package com.example.habittimer.util

import android.content.Context
import com.example.habittimer.model.Habit
import java.util.*

object HabitDataUtil {

    const val FILENAME = "HabitList.json"

    fun initHabitJsonFile(context: Context){
        FileAccessUtil.saveFile(context, FILENAME,"")
    }

    fun readHabitList(context: Context): List<Habit>{
        var list:List<Habit> = listOf()

        FileAccessUtil.readFile(context!!, FILENAME)?.let {
            MoshiUtil.deserializeAsList(it, Habit::class.java)?.let {
                list = it.toMutableList()
            }?: run {
                //when list is empty
            }
        }?: run {
            //first Time of App
           initHabitJsonFile(context)
        }

        return list
    }

    fun saveHabitList(context: Context,list: List<Habit>){
        var jsonString:String

        MoshiUtil.serializeListToJson(list, Habit::class.java)?.let {
            jsonString = it
            FileAccessUtil.saveFile(context, FILENAME,jsonString)
        }?: run {

        }
    }

    fun addHabitToJson(context: Context,habit: Habit){
        var list = readHabitList(context).toMutableList()
        list.add(list.size, habit)
        saveHabitList(context,list)
    }

}