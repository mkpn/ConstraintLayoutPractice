package com.example.yoshidamakoto.constraintlayoutpractice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.ActivityMainBinding
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navigationViewSelectListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.all -> {

                }
                R.id.like -> {

                }
            }
            true
        }
        binding.bottomNavigation.inflateMenu(R.menu.menu)
    }

    override fun onStart() {
        super.onStart()
        launch(UI) {
            try {
                val resBody = async(CommonPool) { getTopPage() }.await()
                binding.textView2.setText(resBody.string())
            } catch (e: CancellationException) {
            }
        }

    }

    suspend fun getTopPage(): ResponseBody = suspendCoroutine { cont ->
        val req = Request.Builder()
                .url("https://ソフトウェアアーキテクトが知るべき97のこと.com")
                .get()
                .build()

        HttpClient.instance.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                cont.resumeWithException(CancellationException())
            }

            override fun onResponse(call: Call?, response: Response?) {
                response?.body()?.let {
                    cont.resume(it)
                }
            }
        })
    }
}
