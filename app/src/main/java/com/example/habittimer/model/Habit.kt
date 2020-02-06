package com.example.habittimer.model

import java.io.Serializable
import java.util.*

data class Habit(
    val name: String,

    val recentDo: Date,

    val cycle: Date

): Serializable