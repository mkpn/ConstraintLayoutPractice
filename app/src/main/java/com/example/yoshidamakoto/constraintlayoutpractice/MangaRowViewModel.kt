package com.example.yoshidamakoto.constraintlayoutpractice

import android.os.Build
import android.text.Spanned

/**
 * Created by yoshidamakoto on 2017/10/15.
 */
class MangaRowViewModel(val manga: Manga) {
    val title = manga.title
    val imageUrl = manga.image
    val score = "Score: ${manga.score}"
    fun getSynopsis(): Spanned? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return android.text.Html.fromHtml(manga.synopsis, android.text.Html.FROM_HTML_MODE_COMPACT)
        } else {
            return android.text.Html.fromHtml(manga.synopsis)
        }
    }

    fun getAiringTerm(): String {
        if (manga.startDate.equals("0000-00-00")) return ""
        if (manga.startDate.equals(manga.endDate)) return "${manga.startDate}(Movie)"
        return "${manga.startDate} ~ ${manga.endDate}"
    }
}