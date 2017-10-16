package com.example.yoshidamakoto.constraintlayoutpractice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    lateinit var binding: ActivityMainBinding
    val animeFragment = AnimeFragment()
    val mangaFragment = MangaFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbar.setTitle(R.string.app_name)
        replaceFragment(animeFragment) // 初期化
        binding.bottomNavigation.inflateMenu(R.menu.menu)
        val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.manga -> {
                    replaceFragment(mangaFragment)
                }
                R.id.anime -> {
                    replaceFragment(animeFragment)
                }
            }
            true
        }
        binding.bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .commitAllowingStateLoss()
    }
}
