package com.example.yoshidamakoto.constraintlayoutpractice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)

        val navigationViewSelectListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.all -> {

                }
                R.id.like -> {

                }
            }
            true
        }
    }
}
