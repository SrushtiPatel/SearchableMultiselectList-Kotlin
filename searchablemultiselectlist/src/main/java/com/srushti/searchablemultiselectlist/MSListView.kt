package com.srushti.searchablemultiselectlist

import android.content.Context
import android.content.res.XmlResourceParser
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet


/**
 * <h1>com.srushti.searchablemultiselectlist</h1>
 *
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 2:42 PM
 */

class MSListView @JvmOverloads constructor(
        mContext: Context,
        mAttrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
) : RecyclerView(mContext, mAttrs, defStyleAttr) {

    private lateinit var mIntLayoutId: XmlResourceParser

    init {
        mAttrs?.let {

        }
    }
}