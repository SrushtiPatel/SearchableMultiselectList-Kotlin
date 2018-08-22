package com.srushti.searchablemultiselectlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.custom_list_row_item_new.view.*


/**
 * <h1>MultiSelectListAdapter</h1>
 *
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 6:35 PM
 */

class MultiSelectListAdapter<T : ListItem>(val context: Context?,
                                           val resource: Int,
                                           objects: ArrayList<T>?
) : RecyclerView.Adapter<MultiSelectListAdapter.MyViewHolder>() {

    private var mData = ArrayList<T>()
    private var mSource = ArrayList<T>()
    private var mSelectedValue = ArrayList<T>()

    init {
        mData = objects as ArrayList<T>
        mSource = ArrayList(mData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, pViewType: Int): MultiSelectListAdapter.MyViewHolder {

        val sView = LayoutInflater.from(context).inflate(resource, parent, false)
        return MyViewHolder(sView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(pViewHolder: MultiSelectListAdapter.MyViewHolder, position: Int) {

        var listItem = getItem(position)
        pViewHolder.mTextView.text = listItem.getDisplayText()

        pViewHolder.mIvSelect.visibility = if (isSelected(listItem)) View.VISIBLE else View.INVISIBLE

        pViewHolder.itemView.tag = listItem
        pViewHolder.itemView.setOnClickListener { view: View ->
            var item = view.tag as T
            toggleSelection(item)
            notifyDataSetChanged()
        }
    }

    private fun toggleSelection(item: T) {
        if (mSelectedValue.contains(item)) {
            mSelectedValue.remove(item)
        } else {
            mSelectedValue.add(item)
        }
    }

    private fun isSelected(item: T): Boolean {
        mSelectedValue.forEach { selected ->
            if (item.listId.equals(selected.listId)) {
                return true
            }
        }

        return false
    }

    fun setSelectedValue(pAlSelectedItem: ArrayList<T>) {
        mSelectedValue = pAlSelectedItem


    }

    fun getSelectedItems(): ArrayList<T> {
        return mSelectedValue
    }

    //
//
    fun getItem(position: Int): T {
        return mData.get(position)
    }

    fun getCount(): Int {
        return mData.size
    }

    fun filter(pSearchText: String?) {
        var search = pSearchText
        mData.clear()
        if (null == search || search.isEmpty()) {
            mData = ArrayList(mSource)
            notifyDataSetChanged()
            return
        }

        mSource.forEach {
            if (it.getDisplayText().toLowerCase().contains(search.toLowerCase())) {
                mData.add(it)
            }
        }
        notifyDataSetChanged()
    }

    class MyViewHolder(pItemView: View) : RecyclerView.ViewHolder(pItemView) {
        val mTextView = pItemView.textview
        val mIvSelect = pItemView.iv_select
    }
}