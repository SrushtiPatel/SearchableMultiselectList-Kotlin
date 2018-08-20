package com.srushti.searchablemultiselectlist

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView


/**
 * <h1>com.srushti.searchablemultiselectlist</h1>
 *
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 6:35 PM
 */

class CustomListAdapter<T:ListItem>(context: Context?,
                        val resource: Int,
                        val textViewResourceId: Int,
                        objects: ArrayList<T>?
) : ArrayAdapter<T>(context, resource, textViewResourceId, objects) {

    private var mData = ArrayList<T>()
    private var mSource = ArrayList<T>()
    private var mSparseBooleanArray: SparseBooleanArray

    init {
        mData = objects as ArrayList<T>
        mSource = ArrayList(mData)
        mSparseBooleanArray = SparseBooleanArray()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(resource, null)
        var textView = view.findViewById<CheckedTextView>(textViewResourceId)
        val item = getItem(position)
        textView.text = item.getDisplayText()
        textView.tag = position

        textView.isChecked = item.isSelected
        textView.setOnClickListener { view: View ->
            mSparseBooleanArray.put(view.getTag() as Int, (view as CheckedTextView).isChecked)
        }
        return view
    }

    fun getSelectedItems(): ArrayList<T> {
        var tempArr: ArrayList<T> = ArrayList()

        for (i in mSource.indices) {
            if (mSparseBooleanArray.get(i)) {
                tempArr.add(mSource.get(i))
            }
        }

        return tempArr
    }
    override fun getItem(position: Int): T {
        return mData.get(position)
    }

    override fun getCount(): Int {
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

        mData = ArrayList()
        mSource.forEach {
            if (it.getDisplayText().toLowerCase().contains(search.toLowerCase())) {
                mData.add(it)
            }
        }
        notifyDataSetChanged()
    }

}