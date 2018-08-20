package com.srushti.searchablemultiselectlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.TextView


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

    init {
        mData = objects as ArrayList<T>
        mSource = ArrayList(mData)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(resource, null)
        var textView = view.findViewById<CheckedTextView>(textViewResourceId)
        textView.text = getItem(position).getDisplayText()

        return view
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
            if (it.toString().toLowerCase().contains(search.toLowerCase())) {
                mData.add(it)
            }
        }
        notifyDataSetChanged()
    }

}