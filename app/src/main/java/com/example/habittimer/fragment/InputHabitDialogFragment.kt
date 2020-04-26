package com.example.habittimer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habittimer.R
import com.example.habittimer.model.Habit
import com.example.habittimer.util.HabitDataUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_input_habit.*
import android.content.Intent
import java.util.*
import android.content.DialogInterface
import com.example.habittimer.model.Cycle
import com.example.habittimer.model.DateUnit


class InputHabitDialogFragment : BottomSheetDialogFragment() {

    val ARG_NAME_NUMBER = "CYCLE_NUMBER"
    val ARG_NAME_UNIT = "CYCLE_UNIT"

    private var cycle = Cycle(1,DateUnit.DAY)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        return inflater.inflate(R.layout.fragment_input_habit, container, false)
    }

    override fun onStart() {
        super.onStart()
        setOnClickAction()
    }
    private fun setOnClickAction(){

        fb_save_habit.setOnClickListener { view ->
            val title = et_habitTitle.text.toString()
            val habit = Habit("0",title, Date(),cycle,null)
            HabitDataUtil.addHabitToJson(context!!, habit)
            fragmentManager!!.beginTransaction().remove(this).commit()
        }


        tv_habit_cycle.setOnClickListener { view ->
            val dialog = CyclePickerDialog()

            val arg = Bundle()
            arg.putInt(ARG_NAME_NUMBER,cycle.number )
            arg.putInt(ARG_NAME_UNIT,cycle.dateUnit.id)

            dialog.arguments = arg
            dialog.setTargetFragment(this, 100)
            dialog.show(fragmentManager!!.beginTransaction(), "CyclePickerDialog")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100) {
            if (resultCode == DialogInterface.BUTTON_POSITIVE) {
                val number = data!!.getIntExtra(ARG_NAME_NUMBER,1)
                val unit = data!!.getIntExtra(ARG_NAME_UNIT,0)

                cycle = Cycle.fromId(number,unit)
                tv_habit_cycle.text = cycle.getStringWithUnit()
            }
        }
    }


    companion object {
        fun newInstance(): InputHabitDialogFragment =
            InputHabitDialogFragment().apply {
            }
    }

}
