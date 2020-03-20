package ru.sailorkenobi.devart

//import android.R
import ru.sailorkenobi.devart.R
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.sailorkenobi.devart.R.layout.activity_main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        navigationView.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{ menuItem ->
            when (menuItem.itemId) {
                R.id.action_newest -> {
                    val fragment = RecentFragment()
                    supportFragmentManager.beginTransaction().replace(container.id, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_newest -> {
                    val fragment = RecentFragment()
                    supportFragmentManager.beginTransaction().replace(container.id, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_newest -> {
                    val fragment = RecentFragment()
                    supportFragmentManager.beginTransaction().replace(container.id, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false }
    }
}
