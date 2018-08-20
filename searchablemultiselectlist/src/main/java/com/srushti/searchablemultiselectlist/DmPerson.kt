package com.srushti.searchablemultiselectlist


/**
 * <h1>com.srushti.searchablemultiselectlist</h1>
 *
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 5:52 PM
 */

public data class DmPerson(val id: String = "0", val name: String = "") : ListItem {
    override var isSelected: Boolean
        get(): Boolean {
            return isSelected
        }
        set(value) {
            isSelected = value
        }

    override fun toString(): String {
//        return name
        return getDisplayText()
    }

    override fun getDisplayText(): String {
        return name
    }
}