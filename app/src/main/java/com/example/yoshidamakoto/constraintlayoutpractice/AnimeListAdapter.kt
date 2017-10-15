package com.example.yoshidamakoto.constraintlayoutpractice

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.AnimeRowBinding

/**
 * Created by yoshidamakoto on 2017/10/15.
 */
class AnimeListAdapter(val context: Context, val animeList: List<Anime>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater by lazy { context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater }

    override fun getItemCount(): Int {
        return animeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val binding = (holder as ContentViewHolder).binding
        binding.anime = animeList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ContentViewHolder(AnimeRowBinding.inflate(inflater, parent, false))
    }

    private class ContentViewHolder(val binding: AnimeRowBinding) : RecyclerView.ViewHolder(binding.root)
}