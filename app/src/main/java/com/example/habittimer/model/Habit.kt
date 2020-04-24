package com.example.habittimer.model

import java.io.Serializable
import java.util.*

enum class HabitState {
    Nomally,
    Recently,
    Past
}
data class Habit(

    val id: String, //Todo なぜidが必要か検証

    val name: String,

    val recentDo: Date,

    val cycle: Int,

    val state: HabitState?  //Todo ステート判定


//    fun

): Serializable
