package ru.sailorkenobi.devart.ui

import ru.sailorkenobi.devart.RecentRecyclerViewAdapter
import ru.sailorkenobi.devart.pollPopularData

class PopularFragment : GalleryFragment() {
    override fun pollData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
        pollPopularData(recyclerViewAdapter)
    }
}
