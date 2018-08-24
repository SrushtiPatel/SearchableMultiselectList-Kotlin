package com.srushti.searchablemultiselectlistview

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.srushti.searchablemultiselectlist.DmPerson
import com.srushti.searchablemultiselectlist.MultiSelectListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mIsLargeLayout: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.inTransaction { add(R.id.) }
        tv_names.setOnClickListener {
            showDialog()
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun showDialog() {
        var arrData = ArrayList<DmPerson>()
        arrData.add(DmPerson("1", "Srushti"))
        arrData.add(DmPerson("2", "Neha"))
        arrData.add(DmPerson("3", "Deepali"))
        arrData.add(DmPerson("4", "Namita"))
        arrData.add(DmPerson("5", "Jhanvi"))
        arrData.add(DmPerson("6", "Hiral"))


        var selectedItems = ArrayList<DmPerson>()
        selectedItems.add(DmPerson("1", "Srushti"))
        selectedItems.add(DmPerson("3", "Deepali"))


//        val newFragment = MultiSelectListFragment.newInstance(arrData, selectedItems)
        val newFragment = MultiSelectListFragment<DmPerson>()
        newFragment.setDataCollection(arrData)
        newFragment.setSelectedItemCollection(selectedItems)

        newFragment.setItemSelectedListener(object : MultiSelectListFragment.ListItemSelectionListener {
            override fun onListItemSelected(pSelectedItem: ArrayList<Any>) {
                Log.e("SP", "Selected items---> " + pSelectedItem.toString())
                tv_names.text = pSelectedItem.toString()
            }

        })
        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(supportFragmentManager, "dialog")
        } else {
            // The device is smaller, so show the fragment fullscreen
            val transaction = supportFragmentManager.beginTransaction()
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(R.id.container, newFragment).addToBackStack(null).commit()
        }
    }
}
