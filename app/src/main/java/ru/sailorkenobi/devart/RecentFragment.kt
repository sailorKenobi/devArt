package ru.sailorkenobi.devart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sailorkenobi.devart.dummy.DummyContent.DummyItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RecentFragment.OnListFragmentInteractionListener] interface.
 */
class RecentFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 2

    private var listener: OnListFragmentInteractionListener? = null

    lateinit var dummyItemsList: MutableList<DummyItem>
    private lateinit var recyclerViewAdapter: RecentRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recent_list, container, false)

        //testRequest()

        var itemsList = mutableListOf<GalleryItem>()
        var dummyItemsList = mutableListOf<DummyItem>()

        // Set the adapter
        if (view is RecyclerView) {
            recyclerViewAdapter = RecentRecyclerViewAdapter(itemsList, listener)
            with(view) {
                layoutManager = GridLayoutManager(context, columnCount)
                adapter = recyclerViewAdapter
            }
        }

        lifecycleScope.launch {
            itemsList = getLatest()
            //Log.d("showItems", "items count ${itemsList.count()}")
            withContext(Dispatchers.Main) {
                recyclerViewAdapter.setItems(itemsList)
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        //
    }

    fun testRequest() {
        lifecycleScope.launch {
            //val res = GetWithToken("https://www.deviantart.com/api/v1/oauth2/browse/newest?limit=5")
            //Log.d("TestResult", res)
            val res = getLatest()
            Log.d("TestResult", res.count().toString())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            //throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            RecentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
