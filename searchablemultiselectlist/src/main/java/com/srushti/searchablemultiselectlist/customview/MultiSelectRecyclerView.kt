package com.srushti.searchablemultiselectlist.customview

import android.content.Context
import android.content.res.XmlResourceParser
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.srushti.searchablemultiselectlist.R


/**
 * <h1>com.srushti.searchablemultiselectlist</h1>
 *
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 2:42 PM
 */

class MultiSelectRecyclerView @JvmOverloads constructor(
        mContext: Context,
        mAttrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(mContext, mAttrs, defStyleAttr) {

    private lateinit var mIntLayoutId: XmlResourceParser
    private var mTextViewId: Int = 0
    var mDisplayTextView: TextView

    init {
        mAttrs?.let {
            val typedArray = mContext.obtainStyledAttributes(it, R.styleable.MultiSelectRecyclerView, 0, defStyleRes)
            mIntLayoutId = resources.getLayout(typedArray.getResourceId(R.styleable.MultiSelectRecyclerView_layout_id, 0))
            mTextViewId = typedArray.getResourceId(R.styleable.MultiSelectRecyclerView_text_view_id, 0)

            typedArray.recycle()
        }

//        LayoutInflater.from(mContext).inflate(mIntLayoutId, this, true)
        LayoutInflater.from(mContext).inflate(R.layout.sms_layout, this, true)

//        mDisplayTextView = findViewById(mTextViewId)
        mDisplayTextView = findViewById(R.id.tv_selected_values)

        mDisplayTextView.setOnClickListener { view: View ->

            //            loadListFragment()

        }
    }

//    private val arrData: ArrayList<Any>
//
//    private fun loadListFragment() {
//
//        val newFragment = MultiSelectListFragment.newInstance(arrData, selectedItems)
//        newFragment.setItemSelectedListener(object : MultiSelectListFragment.ListItemSelectionListener {
//            override fun onListItemSelected(pSelectedItem: ArrayList<Any>) {
//                Log.e("SP", "Selected items---> " + pSelectedItem.toString())
//                tv_names.text = pSelectedItem.toString()
//            }
//
//        }


}