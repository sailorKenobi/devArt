package ru.sailorkenobi.devart.ui

import ru.sailorkenobi.devart.RecentRecyclerViewAdapter
import ru.sailorkenobi.devart.pollRecentData

class RecentFragment : GalleryFragment() {
    override fun pollData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
        pollRecentData(recyclerViewAdapter)
    }
}
