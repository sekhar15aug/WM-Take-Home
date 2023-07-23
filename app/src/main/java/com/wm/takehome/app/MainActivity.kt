package com.wm.takehome.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wm.takehome.ui.list.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.appFrame, ListFragment())
                .commit()
        }
    }
}