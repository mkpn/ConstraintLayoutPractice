package com.example.yoshidamakoto.constraintlayoutpractice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.ActivityMainBinding
import io.reactivex.Observable
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.jsoup.parser.Parser
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
                R.id.all -> {

                }
                R.id.like -> {

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
                val articleList = async(CommonPool) { getTopPage() }.await()
            } catch (e: CancellationException) {
                // jobがキャンセルされるらしい
            }
        }
        // job.cancelを書くべきかわからない・・・;
        // job.cancel()書いたら即キャンセルされるのか動かなくなったんご
    }

    suspend fun getTopPage(): List<Article> = suspendCoroutine { cont ->
        val req = Request.Builder()
                .url("https://ソフトウェアアーキテクトが知るべき97のこと.com")
                .get()
                .build()

        HttpClient.instance.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                cont.resumeWithException(CancellationException())
            }

            override fun onResponse(call: Call?, response: Response?) {
                response?.body()?.string().let {
                    val doc = Parser.parse(it, "")
                    val articleElementList = doc.getElementsByTag("ol")[0].getElementsByTag("a")
                    val articleList = Observable.fromArray(articleElementList)
                            .flatMapIterable { list -> list }
                            .map { Article(it.text(), it.attr("href")) }
                            .toList().blockingGet()

                    cont.resume(articleList)
                }
            }
        })
    }
}
