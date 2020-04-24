package com.example.habittimer.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.habittimer.R
import kotlinx.android.synthetic.main.dialog_input_cycle.view.*
import kotlinx.android.synthetic.main.dialog_input_cycle.view.np_number
import android.app.Activity
import android.content.Context
import kotlinx.android.synthetic.main.dialog_input_cycle.*
import kotlinx.android.synthetic.main.dialog_input_cycle.view.tv_cycle_ok
import kotlinx.android.synthetic.main.fragment_input_habit.*

class CyclePickerDialog : DialogFragment() {
//    private lateinit var dialog: AlertDialog
    private val unit = arrayOf("日","週","ヶ月","年")
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnFragmentInteractionListener
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: return super.onCreateDialog(savedInstanceState)
        val v = activity.layoutInflater.inflate(R.layout.dialog_input_cycle, null)

        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(v)

        initNumberPicker(v)
        initUnitPicker(v)
        v.tv_cycle_cancel.setOnClickListener{
            dialog.dismiss()
        }
        v.tv_cycle_ok.setOnClickListener {
            val num = v.tv_cycle_number.text.toString()
            val unit = v.tv_cycle_unit.text.toString()
            listener?.onClickConfirmOk(num + unit)
            dialog.dismiss()
        }

        val alert = AlertDialog.Builder(this.activity)
            .setPositiveButton(v.tv_cycle_ok.id) { _, _ ->

            }
            .setNegativeButton(v.tv_cycle_cancel.id) { _, _ ->
//                listener?.onClickConfirmCancel(confirmType)
            }
            .setOnCancelListener { dialog ->
                // キャンセルされた場合に実行される処理
            }
            .setOnDismissListener { dialog ->
                // ダイアログが閉じた場合に実行される処理。キャンセルされた場合にも呼び出される
            }
        alert.setView(v)

        dialog.show()
        dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return dialog
    }


    private fun initNumberPicker(v: View){
        v.np_number.wrapSelectorWheel = true
        v.np_number.value = 1
        v.np_number.maxValue = 24
        v.np_number.minValue = 1

        v.tv_cycle_number.text = v.np_number.value.toString()
        v.np_number.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            v.tv_cycle_number.setTextColor(resources.getColor(R.color.WHITE))
            v.tv_cycle_unit.setTextColor(resources.getColor(R.color.GRAY))
            v.tv_cycle_number.text = newVal.toString()
        }
        v.np_unit.setOnFocusChangeListener { view, b ->
            //            v.np_unit.outlineSpotShadowColor(resources.getColor(R.color.colorAccent))

        }
    }

    private fun initUnitPicker(v: View){
        v.np_unit.wrapSelectorWheel = false
        v.np_unit.value = 0
        v.np_unit.wrapSelectorWheel
        v.np_unit.minValue = 0
        v.np_unit.maxValue = unit.size-1


        v.np_unit.displayedValues = unit
        v.tv_cycle_unit.text = unit.get(v.np_unit.value)
        v.np_unit.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            v.tv_cycle_unit.setTextColor(resources.getColor(R.color.WHITE))
            v.tv_cycle_number.setTextColor(resources.getColor(R.color.GRAY))
            v.tv_cycle_unit.text = unit.get(newVal)
        }
//        v.np_unit.setOnTouchListener { view, motionEvent ->
//
//        }
        v.np_unit.setOnFocusChangeListener { view, b ->
//            v.np_unit.outlineSpotShadowColor(resources.getColor(R.color.colorAccent))

        }
    }

    fun setListener(listener: OnFragmentInteractionListener){
        this.listener = listener
    }

    interface OnFragmentInteractionListener {
        fun onClickConfirmOk(string: String)
//        fun onClickConfirmCancel()
    }

    companion object {

        fun newInstance(): CyclePickerDialog {
            return CyclePickerDialog()
        }
    }

}