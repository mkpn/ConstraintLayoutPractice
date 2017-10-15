package com.example.yoshidamakoto.constraintlayoutpractice

/**
 * Created by yoshidamakoto on 2017/10/15.
 */
data class Manga(val id: Long = 0, val contentId: Long, val title: String,
                 val english: String, val synonyms: String,
                 val chapters: Int, val volumes: Int, val score: Float,
                 val startDate: String, val endDate: String,
                 val synopsis: String, val image: String)