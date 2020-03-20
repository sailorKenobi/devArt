package ru.sailorkenobi.devart

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recent_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sailorkenobi.devart.R.drawable.icon_deviantart_512
import ru.sailorkenobi.devart.R.layout.recent_item

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class RecentRecyclerViewAdapter(
    private val mValues: MutableList<GalleryItem>,
    private val mListener: GalleryFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<RecentRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as GalleryItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    fun setItems(newItems: List<GalleryItem>) {
        mValues.clear()
        mValues.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        //holder.titleView.text = item.title
        val drawable = holder.itemView.context.resources.getDrawable(icon_deviantart_512)
        holder.bindDrawable(drawable)

        GlobalScope.launch {
            val image = loadImage(item.preview)
            withContext(Dispatchers.Main) {
                if (image != null)
                    holder.bindBitmap(image)
            }
        }


        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    suspend fun loadImage(url: String?): Bitmap? {
         val res = withContext(Dispatchers.IO) {
             val res = url?.let { GetImage(it) }
             if (res != null)
                 return@withContext res
             else {
                 Log.d("loadImage", "${url} failed")
                 return@withContext null
             }
         }
        return res
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(recent_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val imageView: ImageView = mView.item_image

        fun bindDrawable(drawable: Drawable) {
            imageView.setImageDrawable(drawable)
        }

        fun bindBitmap(bitmap: Bitmap) {
            imageView.setImageBitmap(bitmap)
        }
    }
}
