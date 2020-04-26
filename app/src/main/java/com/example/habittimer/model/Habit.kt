package com.example.habittimer.model

import java.io.Serializable
import java.util.*



enum class HabitState {
    NORMALY,
    RECENTLY,
    PAST
}


data class Habit(

    val id: String, //Todo なぜidが必要か検証

    val name: String,

    val recentDo: Date,

    val cycle: Cycle,

    val state: HabitState?

): Serializable
