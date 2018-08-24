package com.srushti.searchablemultiselectlist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView


open class MultiSelectListFragment<E : ListItem> : DialogFragment() {

    val KEY_LIST_SOURCE: String = "source_data"
    val KEY_SELECTED_DATA: String = "selected_data"

    private val mIntLayout: Int = R.layout.fragment_custom_list

    private lateinit var mDialog: AlertDialog

    private var arrData: ArrayList<E> = ArrayList()
    private var selectedItems: ArrayList<E> = ArrayList()
    private lateinit var mCustomListAdapter: MultiSelectListAdapterExtended<E>
    private lateinit var mItemSelectionListener: ListItemSelectionListener<E>
    private var mStrTitle: String? = null

    lateinit var my_list_view: MSListView
    lateinit var tv_done: TextView
    lateinit var tv_title: TextView
    lateinit var search_view: SearchView

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun <T : ListItem> newInstance(pSourceData: ArrayList<T>, pSelectedValues: ArrayList<T>) =
                MultiSelectListFragment<T>().apply {
                    arguments = Bundle().apply {
                        //                        putParcelableArrayList(KEY_LIST_SOURCE, pSourceData)
//                        putParcelableArrayList(KEY_SELECTED_DATA, pSelectedValues)
                    }
                }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBundleParams()
    }


//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_custom_list, container, false);
//    }

    /** The system calls this only when creating the layout in a dialog.  */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sView = LayoutInflater.from(activity).inflate(mIntLayout, null)

        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
//        val dialog = super.onCreateDialog(savedInstanceState)
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(sView)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setTitle(null)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

//        val lWindowParams = WindowManager.LayoutParams()
//        lWindowParams.copyFrom(alertDialog.window.attributes)
//        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT // this is where the magic happens
//        lWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT
//
//        alertDialog.window.attributes = lWindowParams
        mapUIElement(sView)

        mDialog = alertDialog
        return alertDialog
    }

    private fun mapUIElement(sView: View) {
        search_view = sView.findViewById(R.id.search_view)
        my_list_view = sView.findViewById(R.id.my_list_view)
        tv_done = sView.findViewById(R.id.tv_done)
        tv_title = sView.findViewById(R.id.tv_title)

        initializeView()
    }


//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val rootView = LayoutInflater.from(context).inflate(mIntLayout, null)
//
//        mapUIElement(rootView)
//        initializeView()
//        return rootView
//    }

    private fun initializeView() {

        setCustomTitle()
        setSearchView()
        setListAdapter()
    }

    override fun getShowsDialog(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setCustomTitle() {
//        tv_title.text = "Select Name"
        mStrTitle?.let { getString(R.string.title_select) }
        tv_title.text = mStrTitle

        tv_done.setOnClickListener {
            mItemSelectionListener.onListItemSelected(mCustomListAdapter.getSelectedItems())
            mDialog?.dismiss()
        }
    }

    private fun setBundleParams() {
//        arrData = arguments?.getParcelableArrayList(KEY_LIST_SOURCE) ?: ArrayList()
//        selectedItems = arguments?.getParcelableArrayList(KEY_SELECTED_DATA) ?: ArrayList()

    }

    public fun setDataCollection(pArrDataSource: ArrayList<E>) {
        arrData = pArrDataSource
//        notifyListDataChange()
    }

    public fun setSelectedItemCollection(pArrSelectedItem: ArrayList<E>) {
        selectedItems = pArrSelectedItem
//        notifyListDataChange()
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

    fun setItemSelectedListener(pListener: ListItemSelectionListener<E>) {
        mItemSelectionListener = pListener
    }


    private fun setListAdapter() {
        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        my_list_view.layoutManager = layoutManager

//        var selectedItems = ArrayList<DmPerson>()
//        selectedItems.add(DmPerson("1", "Srushti"))
//        selectedItems.add(DmPerson("3", "Deepali"))
        filterSelectedData()

        mCustomListAdapter = MultiSelectListAdapterExtended(context, R.layout.custom_list_row_item_new, arrData)
        mCustomListAdapter.setSelectedValue(selectedItems)
        my_list_view.adapter = mCustomListAdapter


//        my_list_view.setOnItemClickListener { adapterView: View, view, position: Int, l ->
//            Log.e("SP", "Selected Data ----> " + mCustomListAdapter.getSelectedItems())
//        }
    }

    private fun filterSelectedData() {
        if (selectedItems.isNotEmpty()) {

            arrData.removeAll(selectedItems)
            arrData.addAll(0, selectedItems)

        }
    }

    private fun notifyListDataChange() {
        filterSelectedData()
        mCustomListAdapter.notifyDataSetChanged()
    }

    fun setSelectedItemCollection(pAlSelectedItemString: List<String>) {
        selectedItems = ArrayList()
        pAlSelectedItemString.forEach { itOuter ->
            arrData.forEach { itInner ->
                if (itInner.getDisplayText().contentEquals(itOuter)) {
                    selectedItems.add(itInner)
                }
            }
        }
    }

    fun buildDisplayString(): String {
        var sbString = StringBuilder()
        var bFoundFirst: Boolean = false
        sbString.append("[")
        selectedItems.forEach {
            //            var str : String = "[$it]"
            if (bFoundFirst) {
                sbString.append("], [")
            }
            bFoundFirst = true
            sbString.append(it)

        }
        sbString.append("]")

        return sbString.toString()
    }

    fun getSelectedItemStringList(): List<String> {
        val sAlSelectedStrings = ArrayList<String>()
        selectedItems.forEach {
            //            var str : String = "[$it]"
            sAlSelectedStrings.add(it.toString())
        }

        return sAlSelectedStrings
    }

    fun getSelectedItemIndexList(): List<Int> {
        return selectedItems.indices.toList()
    }


    fun setTitle(pStrTitle: String) {
        mStrTitle = pStrTitle
    }


    interface ListItemSelectionListener<T> {
        fun onListItemSelected(pSelectedItem: ArrayList<T>)
    }
}

private fun Any.forEach(action: (Int) -> Boolean) {

}
