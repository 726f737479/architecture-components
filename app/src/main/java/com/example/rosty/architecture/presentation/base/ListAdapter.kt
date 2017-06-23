package com.example.rosty.architecture.presentation.base

import android.content.Context
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by rosty on 6/22/17.
 */

abstract class ListAdapter<T, DB : ViewDataBinding>(

        @LayoutRes private val layoutRes: Int,

        protected var data:   ObservableList<T>?,
        protected var action: ItemAction<T>

) : RecyclerView.Adapter<ViewHolder<DB>>() {

    private val changesCallback: ObservableList.OnListChangedCallback<ObservableList<T>>

    init {

        changesCallback = object : ObservableList.OnListChangedCallback<ObservableList<T>>() {

            override fun onChanged(sender: ObservableList<T>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notifyItemRangeRemoved(positionStart, itemCount)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<DB> {

        return ViewHolder(getLayoutInflater(parent.context).inflate(
                layoutRes, parent, false))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    fun getItem(position: Int): T? {
        return if (data != null && position < data!!.size) data!![position] else null
    }

    @CallSuper fun updateData(data: ObservableList<T>) {

        this.data = data

        this.data!!.addOnListChangedCallback(changesCallback)
    }

    private fun getLayoutInflater(context: Context): LayoutInflater {
        return context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}
