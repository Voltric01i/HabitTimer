package com.example.habittimer.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.habittimer.R
import kotlinx.android.synthetic.main.dialog_input_period.view.*
import android.widget.NumberPicker
import androidx.annotation.UiThread
import kotlinx.android.synthetic.main.dialog_input_period.*
import kotlinx.android.synthetic.main.dialog_input_period.view.np_number


class PeriodPickerDialog : DialogFragment() {
//    private lateinit var dialog: AlertDialog
    private val unit = arrayOf("日","週","ヶ月","年")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return null
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: return super.onCreateDialog(savedInstanceState)
        val alert = AlertDialog.Builder(this.activity)
        val v = activity.layoutInflater.inflate(R.layout.dialog_input_period, null)

        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(v)

        initNumberPicker(v)
        initUnitPicker(v)
        v.tv_period_cancel.setOnClickListener{
            dialog.dismiss()
        }
        v.tv_period_ok.setOnClickListener {
//            RemoteConfigUtil.initRemoteConfig {
//                val urlString = RemoteConfigUtil.remoteConfig.getString("premium_member_introduction_url")
//                val uri = Uri.parse(urlString)
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(intent)
//            }
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

        v.np_number.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            v.tv_period_number.textSize = 60.0f
            v.tv_period_unit.textSize = 30.0f
            v.tv_period_number.text = newVal.toString()
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
        v.np_unit.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            v.tv_period_unit.textSize = 60.0f
            v.tv_period_number.textSize = 30.0f
            v.tv_period_unit.text = unit.get(newVal)
        }
//        v.np_unit.setOnTouchListener { view, motionEvent ->
//
//        }
        v.np_unit.setOnFocusChangeListener { view, b ->
//            v.np_unit.outlineSpotShadowColor(resources.getColor(R.color.colorAccent))

        }
    }

    companion object {

        fun newInstance(): PeriodPickerDialog {
            return PeriodPickerDialog()
        }
    }

}