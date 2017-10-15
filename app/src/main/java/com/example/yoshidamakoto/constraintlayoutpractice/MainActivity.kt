package com.example.yoshidamakoto.constraintlayoutpractice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.inflateMenu(R.menu.menu)
        val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.manga -> {

                }
                R.id.anime -> {

                }
            }
            true
        }
        binding.bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()
        launch(UI) {
            try {
                val animeList = async(CommonPool) { getTopPage() }.await()
                binding.animeList.adapter = AnimeListAdapter(this@MainActivity, animeList)
            } catch (e: CancellationException) {
                // jobがキャンセルされるらしい
            }
        }
        // job.cancelを書くべきかわからない・・・;
        // job.cancel()書いたら即キャンセルされるのか動かなくなったんご
    }

    suspend fun getTopPage(): List<Anime> = suspendCoroutine { cont ->
        val req = Request.Builder()
                .url("http://10.0.2.2:8080/anime/all.json")
                .get()
                .build()

        HttpClient.instance.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                cont.resumeWithException(CancellationException())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val animeList = ArrayList<Anime>()
                val gson = Gson()
                response?.body()?.let {
                    val json = response.body().string()
                    val jsonArray = JSONArray(json)
                    (0 until jsonArray.length()).mapTo(animeList) {
                        gson.fromJson(jsonArray.get(it).toString())
                    }
                }
                cont.resume(animeList)
            }
        })
    }
}
