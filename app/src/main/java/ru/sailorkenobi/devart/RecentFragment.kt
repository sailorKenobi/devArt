package ru.sailorkenobi.devart

class RecentFragment : GalleryFragment() {
    override fun pollData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
        pollRecentData(recyclerViewAdapter)
    }
}
