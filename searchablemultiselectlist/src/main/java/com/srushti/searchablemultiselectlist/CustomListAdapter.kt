package com.srushti.searchablemultiselectlist

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CheckedTextView


/**
 * <h1>CustomListAdapter</h1>
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
    private var mSelectedValue = ArrayList<T>()
//    private var mSparseBooleanArray: SparseBooleanArray

    init {
        mData = objects as ArrayList<T>
        mSource = ArrayList(mData)
//        mSparseBooleanArray = SparseBooleanArray()

    }

    fun setSelectedValue(pAlSelectedItem : ArrayList<T>) {
        mSelectedValue = pAlSelectedItem

        setSelectedValues()
    }
    private fun setSelectedValues() {
        mSource.indices.forEach { source ->
//            mSparseBooleanArray.put(it.listId, mSelectedValue.contains(it))
            mSelectedValue.forEach{ selected ->
//                source.isSelected = source.listId.contentEquals(selected.listId)
                if(mSource.get(source).listId.equals(selected.listId)){
                    mSource.get(source).isSelected = true
                    mData.get(source).isSelected = true
//                    break
                }
            }
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(resource, null)
        var textView = view.findViewById<CheckedTextView>(textViewResourceId)
        var item = getItem(position)
        textView.text = item.getDisplayText()
        textView.tag = position


        textView.isChecked = item.isSelected
        view.isSelected = textView.isChecked

        view.tag = position



        textView.setOnClickListener { view: View ->
            Log.e("SP", "Clicked");
            val checkedTextView = view as CheckedTextView
            checkedTextView.toggle()
            var item = getItem(view.tag as Int)
            item.isSelected = checkedTextView.isChecked
            mSource.get(getSourceItemPosition(item.listId)).isSelected = checkedTextView.isChecked
//            notifyDataSetChanged()
        }

//        view.setOnClickListener { view: View ->
//            val position:Int = view.tag as Int
//            val checked = (view as CheckedTextView).isChecked
////            mSparseBooleanArray.put(position, checked)
//            mData.get(position).isSelected = checked
//
//        }
        return view
    }

    private fun getSourceItemPosition(listId: String) : Int {
        mSource.indices.forEach{
            if(listId.equals(mSource.get(it).listId)){
                return it
            }
        }
        return 0
    }

    private fun findItem(item: T): Boolean {
        mSource.forEach{
            if(item.listId.equals(it.listId)){
                return true
            }
        }
        return false
    }

    fun getSelectedItems(): ArrayList<T> {
        var tempArr: ArrayList<T> = ArrayList()

        mSource.indices.forEach { i ->
//            if (mSparseBooleanArray.get(i)) {
//                tempArr.add(mSource.get(i))
//            }
        }

        return mSelectedValue
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