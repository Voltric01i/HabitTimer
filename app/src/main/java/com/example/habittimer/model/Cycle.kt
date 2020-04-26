package com.example.habittimer.model


enum class DateUnit(val id: Int, val text: String, val value: Int){
    DAY(0,"日",1),
    WEEK(1,"週間",7),
    MONTH(2,"ヶ月",30),
    YEAR(3,"年",365);

}
data class Cycle(

    val number: Int,
    val dateUnit: DateUnit

){
    companion object {
        fun fromId(value: Int, unit_id: Int): Cycle{
            val dateUnit = DateUnit.values()[unit_id]
            return Cycle(value,dateUnit)
        }
    }

    fun getStringWithUnit(): String {
        return number.toString() + dateUnit.text
    }

    fun getValue(): Int{
        return number * this.dateUnit.value
    }

}