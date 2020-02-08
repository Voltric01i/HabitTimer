package com.example.habittimer.model

import java.io.Serializable
import java.util.*

object HabitDummy{

    val ITEMS: MutableList<Habit> = ArrayList()
    val ITEM_MAP: MutableMap<String, Habit> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createHabit(i))
        }
    }

    private fun addItem(item: Habit) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createHabit(position: Int): Habit {
        return Habit(
            position.toString(),
            "Item " + position,
            Date(),
            Date()
        )
    }


    data class Habit(
        val id: String,

        val name: String,

        val recentDo: Date,

        val cycle: Date

    ): Serializable
}
