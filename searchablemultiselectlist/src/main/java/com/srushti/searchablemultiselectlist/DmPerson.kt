package com.srushti.searchablemultiselectlist

import android.os.Parcel
import android.os.Parcelable


/**
 * <h1>com.srushti.searchablemultiselectlist</h1>
 *
 *
 * @author Srushti Patel (srushtip@meditab.com) Meditab Software Inc.
 * @since 20/8/18 5:52 PM
 */

public data class DmPerson(val id: String = "0", val name: String = "") : ListItem(), Parcelable {
    override var listId: String
        get() = id
        set(value) {
            id
        }

    override fun toString(): String {
//        return name
        return getDisplayText()
    }

    override fun getDisplayText(): String {
        return name
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DmPerson> = object : Parcelable.Creator<DmPerson> {
            override fun createFromParcel(source: Parcel): DmPerson = DmPerson(source)
            override fun newArray(size: Int): Array<DmPerson?> = arrayOfNulls(size)
        }
    }
}