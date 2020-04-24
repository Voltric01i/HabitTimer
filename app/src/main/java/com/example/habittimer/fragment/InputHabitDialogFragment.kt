package com.example.habittimer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.habittimer.R
import com.example.habittimer.model.Habit
import com.example.habittimer.util.HabitDataUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_input_habit.*
import android.content.Intent
import android.app.Activity
import android.util.Log
import androidx.navigation.ui.AppBarConfiguration
import java.util.*


class InputHabitDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        return inflater.inflate(R.layout.fragment_input_habit, container, false)
    }

    override fun onStart() {
        super.onStart()
        setOnClickAction()
    }
    private fun setOnClickAction(){
        fb_save_habit.setOnClickListener { view ->
            val title = et_habitTitle.text.toString()
            val habit = Habit("0",title, Date(),14,null)
            HabitDataUtil.addHabitToJson(context!!, habit)
            fragmentManager!!.beginTransaction().remove(this).commit()
        }
        tv_habit_cycle.setOnClickListener { view ->
            CyclePickerDialog.newInstance().show(fragmentManager!!.beginTransaction(), "PeriodPickerDialog")
        }
    }

    companion object {
        fun newInstance(): InputHabitDialogFragment =
            InputHabitDialogFragment().apply {
            }
    }


}
