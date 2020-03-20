package ru.sailorkenobi.devart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sailorkenobi.devart.GalleryItem
import ru.sailorkenobi.devart.R
import ru.sailorkenobi.devart.RecentRecyclerViewAdapter

abstract class GalleryFragment : Fragment() {

    private var columnCount = 3

    private lateinit var recyclerViewAdapter: RecentRecyclerViewAdapter

    abstract fun pollData(recyclerViewAdapter: RecentRecyclerViewAdapter)

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

        var itemsList = mutableListOf<GalleryItem>()
        // Set the adapter
        if (view is RecyclerView) {
            recyclerViewAdapter =
                RecentRecyclerViewAdapter(itemsList, null)
            with(view) {
                layoutManager = GridLayoutManager(context, columnCount)
                adapter = recyclerViewAdapter
            }
        }

        pollData(recyclerViewAdapter)

        return view
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: GalleryItem?)
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
