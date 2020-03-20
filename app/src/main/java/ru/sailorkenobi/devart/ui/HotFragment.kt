package ru.sailorkenobi.devart.ui

import ru.sailorkenobi.devart.RecentRecyclerViewAdapter
import ru.sailorkenobi.devart.pollHotData

class HotFragment : GalleryFragment() {
    override fun pollData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
        pollHotData(recyclerViewAdapter)
    }
}
