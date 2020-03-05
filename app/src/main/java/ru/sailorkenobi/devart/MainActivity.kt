package ru.sailorkenobi.devart

import androidx.fragment.app.Fragment

class MainActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return GalleryFragment()
    }

}
