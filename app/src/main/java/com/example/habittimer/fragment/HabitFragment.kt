package com.example.habittimer.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habittimer.R
import com.example.habittimer.adapter.HabitRecyclerViewAdapter
import com.example.habittimer.model.Habit
import com.example.habittimer.util.FileAccessUtil
import com.example.habittimer.util.MoshiUtil
import java.util.*


class HabitFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_habit_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = HabitRecyclerViewAdapter(
                    setDataFromFile(),
                    listener
                )
            }
        }
        return view
    }

    fun setDataFromFile(): List<Habit>{
        var readData = mutableListOf<Habit>()

        FileAccessUtil.readFile(context!!,"HabitList.json")?.let {
            MoshiUtil.deserializeAsList(it,Habit::class.java)?.let {
                readData = it.toMutableList()
            }?: run {
                readData.add(Habit("0","中身が空",Date(),Date(),null))
            }
        }?: run { //First Time of App
            FileAccessUtil.saveFile(context!!,"HabitList.json","")
            readData.add(Habit("0","アプリが最初に起動",Date(),Date(),null))
        }

        return readData
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Habit?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            HabitFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
