package com.demo.justapp.exchanger.presentation.ui.adapter.extension

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface DiffUtilAdapter {

    fun <T> RecyclerView.Adapter<*>.autoUpdate(old: List<T>, new: List<T>, compare: (T, T) -> Boolean) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
                return compare(old[oldPosition], new[newPosition])
            }

            override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
                return old[oldPosition] == new[newPosition]
            }

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

        })

        diff.dispatchUpdatesTo(this)
    }

}