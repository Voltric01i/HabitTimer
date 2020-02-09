package com.example.habittimer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habittimer.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


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
