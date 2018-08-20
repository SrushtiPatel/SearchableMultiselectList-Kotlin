package com.srushti.searchablemultiselectlist

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_custom_list.*


class CustomListFragment : DialogFragment() {

    private lateinit var arrData: ArrayList<DmPerson>
    private lateinit var mCustomListAdapter: CustomListAdapter<DmPerson>

    fun newInstance() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_custom_list, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSearchView()
        arrData = ArrayList()
        arrData.add(DmPerson("1", "Srushti"))
        arrData.add(DmPerson("2", "Neha"))
        arrData.add(DmPerson("3", "Deepali"))
        arrData.add(DmPerson("3", "Namita"))
        arrData.add(DmPerson("3", "Jhanvi"))
        arrData.add(DmPerson("3", "Hiral"))

        setListAdapter()
    }


    private fun setSearchView() {
        search_view.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(pStrInput: String?): Boolean {
                mCustomListAdapter.filter(pStrInput)
                return true
            }

            override fun onQueryTextSubmit(pStrInput: String?): Boolean {
                return true
            }
        })
    }

    private fun setListAdapter() {

        my_list_view.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        mCustomListAdapter = CustomListAdapter(context, R.layout.custom_list_row_item, android.R.id.text1, arrData)
        my_list_view.adapter = mCustomListAdapter

        my_list_view.setOnClickListener { view: View ->
            Log.e("SP", "Selected Data ----> " + mCustomListAdapter.getSelectedItems())
        }
    }
}
