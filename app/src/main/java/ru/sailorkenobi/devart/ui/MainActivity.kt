package ru.sailorkenobi.devart.ui

//import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.sailorkenobi.devart.R
import ru.sailorkenobi.devart.R.layout.activity_main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        setTitle(R.string.title_recent)
        loadFragment(RecentFragment())

        navigationView.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{ menuItem ->
            when (menuItem.itemId) {
                R.id.action_newest -> {
                    setTitle(R.string.title_recent)
                    loadFragment(RecentFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_hot -> {
                    setTitle(R.string.title_hot)
                    loadFragment(HotFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_popular -> {
                    setTitle(R.string.title_popular)
                    loadFragment(PopularFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false }
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().
            replace(container.id, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }
}
