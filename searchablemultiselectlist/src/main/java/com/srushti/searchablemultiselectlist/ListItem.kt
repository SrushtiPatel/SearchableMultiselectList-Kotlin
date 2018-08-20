package com.srushti.searchablemultiselectlist


/**
 * <h1>com.srushti.searchablemultiselectlist</h1>
 *
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 5:52 PM
 */

interface ListItem {
    var isSelected: Boolean

    abstract fun getDisplayText() : String
}