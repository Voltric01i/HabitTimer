package com.example.habittimer.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.habittimer.fragment.HabitFragment.OnListFragmentInteractionListener
import com.example.habittimer.R
import com.example.habittimer.model.Habit
import kotlinx.android.synthetic.main.fragment_habit.view.*
import kotlinx.android.synthetic.main.fragment_input_habit.view.*

class HabitRecyclerViewAdapter(
    private val mValues: List<Habit>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<HabitRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Habit
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_habit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.recentDo.toString()
        holder.mCycleView.text = "周期 : " + item.cycle.getStringWithUnit()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.tv_habit_title
        val mContentView: TextView = mView.tv_recent_do
        val mCycleView: TextView = mView.tv_habit_cycle_view

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
