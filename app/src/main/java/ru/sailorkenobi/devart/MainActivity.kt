package ru.sailorkenobi.devart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        //return GalleryFragment()
        return RecentFragment()
    }

}
