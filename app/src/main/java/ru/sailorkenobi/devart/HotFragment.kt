package ru.sailorkenobi.devart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sailorkenobi.devart.model.Result
import ru.sailorkenobi.devart.model.ResultsList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HotFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotFragment : Fragment() {
    private var columnCount = 3

    private var listener: RecentFragment.OnListFragmentInteractionListener? = null

    private lateinit var recyclerViewAdapter: RecentRecyclerViewAdapter

    private lateinit var getHotDataService: GetHotDataService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getHotDataService = RetrofitInstance.retrofitInstance!!.create<GetHotDataService>(
            GetHotDataService::class.java
        )

        arguments?.let {
            columnCount = it.getInt(RecentFragment.ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        var recentCall = getHotDataService.get("Bearer $api_token")
        if (recentCall != null) {
            recentCall.enqueue(object : Callback<ResultsList?> {
                override fun onResponse(
                    call: Call<ResultsList?>,
                    response: Response<ResultsList?>
                ) {

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
}
