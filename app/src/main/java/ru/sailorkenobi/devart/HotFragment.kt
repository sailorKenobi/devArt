package ru.sailorkenobi.devart

class HotFragment : GalleryFragment() {
    override fun pollData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
        pollHotData(recyclerViewAdapter)
    }
}
