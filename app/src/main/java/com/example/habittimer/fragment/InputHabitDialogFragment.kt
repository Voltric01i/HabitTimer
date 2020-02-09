package com.example.habittimer.fragment

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.habittimer.R
import kotlinx.android.synthetic.main.fragment_input_habit.*

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    InputHabitDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class InputHabitDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input_habit, container, false)
    }


    companion object {
        fun newInstance(): InputHabitDialogFragment =
            InputHabitDialogFragment().apply {}

    }
}
