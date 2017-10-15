package com.example.yoshidamakoto.constraintlayoutpractice

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.yoshidamakoto.constraintlayoutpractice.databinding.MangaRowBinding

/**
 * Created by yoshidamakoto on 2017/10/15.
 */
class MangaListAdapter(val context: Context, mangaList: List<Manga>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val vmList: List<MangaRowViewModel>

    init {
        vmList = mangaList.map { MangaRowViewModel(it) }
    }

    private val inflater: LayoutInflater by lazy { context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater }

    override fun getItemCount(): Int {
        return vmList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val binding = (holder as ContentViewHolder).binding
        if (position == 0) {
            binding.topMargin.visibility = View.VISIBLE
        } else {
            binding.topMargin.visibility = View.GONE
        }

        val vm = vmList[position]

        binding.vm = vm
        Glide.with(binding.image).load(vm.imageUrl).into(binding.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ContentViewHolder(MangaRowBinding.inflate(inflater, parent, false))
    }

    private class ContentViewHolder(val binding: MangaRowBinding) : RecyclerView.ViewHolder(binding.root)
}