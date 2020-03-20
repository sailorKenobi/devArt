package ru.sailorkenobi.devart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sailorkenobi.devart.RetrofitInstance.retrofitInstance
import ru.sailorkenobi.devart.model.Result
import ru.sailorkenobi.devart.model.ResultsList


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RecentFragment.OnListFragmentInteractionListener] interface.
 */
class RecentFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 3

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var recyclerViewAdapter: RecentRecyclerViewAdapter

    private lateinit var getNewestDataService: GetNewestDataService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getNewestDataService = retrofitInstance!!.create<GetNewestDataService>(
            GetNewestDataService::class.java
        )

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
            recyclerViewAdapter = RecentRecyclerViewAdapter(itemsList, listener)
            with(view) {
                layoutManager = GridLayoutManager(context, columnCount)
                adapter = recyclerViewAdapter
            }
        }

        var recentCall = getNewestDataService.get("Bearer $api_token")
        if (recentCall != null) {
            recentCall.enqueue(object : Callback<ResultsList?> {
                override fun onResponse(
                    call: Call<ResultsList?>,
                    response: Response<ResultsList?>
                ) {
                    Log.d("RetroFit", "onResponse")

                    val results =  processApiResults(response.body()?.results)
                    recyclerViewAdapter.setItems(results)
                }

                override fun onFailure(call: Call<ResultsList?>?, t: Throwable) {
                    Log.d("RetroFit", "onFailure")
                }
            })
        }

        return view
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
