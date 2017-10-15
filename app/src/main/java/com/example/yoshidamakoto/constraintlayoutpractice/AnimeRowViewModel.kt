package com.example.yoshidamakoto.constraintlayoutpractice

import android.os.Build
import android.text.Spanned

/**
 * Created by yoshidamakoto on 2017/10/15.
 */
class AnimeRowViewModel(val anime: Anime) {
    val title = anime.title
    val imageUrl = anime.image
    val score = "Score: ${anime.score}"
    fun getSynopsis(): Spanned? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return android.text.Html.fromHtml(anime.synopsis, android.text.Html.FROM_HTML_MODE_COMPACT)
        } else {
            return android.text.Html.fromHtml(anime.synopsis)
        }
    }

    fun getAiringTerm(): String {
        if (anime.startDate.equals("0000-00-00")) return ""
        if (anime.startDate.equals(anime.endDate)) return "${anime.startDate}(Movie)"
        return "${anime.startDate} ~ ${anime.endDate}"
    }
}