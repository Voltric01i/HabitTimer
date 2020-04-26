package com.example.habittimer.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_input_cycle.view.*
import kotlinx.android.synthetic.main.dialog_input_cycle.view.np_number
import com.example.habittimer.R
import com.example.habittimer.model.Cycle
import com.example.habittimer.model.DateUnit
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class CyclePickerDialog : DialogFragment() {
    val ARG_NAME_NUMBER = "CYCLE_NUMBER"
    val ARG_NAME_UNIT = "CYCLE_UNIT"

    private val unit = arrayOf("日","週","ヶ月","年")
    //TODO これはあたまがよくない
    private var cycle = Cycle(1,DateUnit.DAY)



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(activity,R.style.AlertDialog)
        initArgValue()

        activity?.let {
            val view = it.layoutInflater.inflate(R.layout.dialog_input_cycle, null)
            initNumberPicker(view)
            initUnitPicker(view)
            builder.setView(view)
                .setPositiveButton(R.string.ok) { _, _->
                    val number = view.np_number.value
                    val unit = view.np_unit.value
                    cycle = Cycle.fromId(number,unit)

                    val intent = Intent()
                    intent.putExtra(ARG_NAME_NUMBER, cycle.number)
                    intent.putExtra(ARG_NAME_UNIT, cycle.dateUnit.id)
                    targetFragment!!.onActivityResult(targetRequestCode, -1, intent)
                }
                .setNegativeButton(R.string.cancel) { _, _->

                }
        }
        return builder.create()


    }

    private fun initArgValue(){
        val argNumber = arguments!!.getInt(ARG_NAME_NUMBER,1)
        val argUnit = arguments!!.getInt(ARG_NAME_UNIT,0)

        cycle = Cycle.fromId(argNumber,argUnit)
    }

    private fun initNumberPicker(v: View){
        v.np_number.value = cycle.number
        //TODO 初期値を入れてもNumber Pickerに反映されない
        v.np_number.maxValue = 24
        v.np_number.minValue = 1

        v.tv_cycle_number.text = v.np_number.value.toString()
        v.np_number.setOnValueChangedListener { numberPicker, oldVal, newVal ->

            v.tv_cycle_number.setTextColor(resources.getColor(R.color.WHITE))
            v.tv_cycle_unit.setTextColor(resources.getColor(R.color.GRAY))
            v.tv_cycle_number.text = newVal.toString()

        }

    }

    private fun initUnitPicker(v: View){
        v.np_unit.value = cycle.dateUnit.id
        //TODO 初期値を入れてもNumber Pickerに反映されない
        v.np_unit.minValue = 0
        v.np_unit.maxValue = DateUnit.values().size-1

        v.np_unit.displayedValues = unit
        v.tv_cycle_unit.text = unit.get(v.np_unit.value)
        v.np_unit.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            v.tv_cycle_unit.setTextColor(resources.getColor(R.color.WHITE))
            v.tv_cycle_number.setTextColor(resources.getColor(R.color.GRAY))
            v.tv_cycle_unit.text = unit.get(newVal)
        }

    }

    companion object {

        fun newInstance(): CyclePickerDialog {
            return CyclePickerDialog()
        }
    }

}