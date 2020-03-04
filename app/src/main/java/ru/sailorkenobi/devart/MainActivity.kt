package ru.sailorkenobi.devart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val recentFragment = RecentFragment()
        fragmentTransaction.replace(R.id.main_fragment_container, recentFragment)
        fragmentTransaction.commit()
    }
}
