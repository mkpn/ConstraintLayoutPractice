package com.example.yoshidamakoto.constraintlayoutpractice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.AnimeFragmentBinding
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

/**
 * Created by yoshidamakoto on 2017/10/16.
 */
class AnimeFragment : Fragment() {

    lateinit var binding: AnimeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.anime_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = AnimeFragmentBinding.bind(view)

        launch(UI) {
            try {
                val animeList = async(CommonPool) { getTopPage() }.await()
                binding.animeList.adapter = AnimeListAdapter(getContext(), animeList)
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