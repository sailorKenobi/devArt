package ru.sailorkenobi.devart.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_image.*
import ru.sailorkenobi.devart.R

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        if (image_fragment_container != null) {
            if (savedInstanceState != null)
                return

            val fragment = ImageFragment()
            fragment.arguments = intent.extras

            supportFragmentManager.beginTransaction()
                .add(R.id.image_fragment_container, fragment)
                .commit()
        }
    }

}
