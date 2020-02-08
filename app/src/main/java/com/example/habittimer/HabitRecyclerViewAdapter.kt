package com.example.habittimer

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.habittimer.HabitFragment.OnListFragmentInteractionListener
import com.example.habittimer.model.HabitDummy

import kotlinx.android.synthetic.main.fragment_habit.view.*

class HabitRecyclerViewAdapter(
    private val mValues: List<HabitDummy.Habit>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<HabitRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as HabitDummy.Habit
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
        holder.mIdView.text = item.id
        holder.mContentView.text = item.recentDo.toString()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.et_habit_title
        val mContentView: TextView = mView.et_recent_do

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
