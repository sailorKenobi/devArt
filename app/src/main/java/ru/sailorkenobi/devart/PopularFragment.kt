package ru.sailorkenobi.devart

class PopularFragment : GalleryFragment() {
    override fun pollData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
        pollPopularData(recyclerViewAdapter)
    }
}
