package com.example.yoshidamakoto.constraintlayoutpractice

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View

/**
 * Created by yoshidamakoto on 2017/09/27.
 */
class AllContentAdapter(val context: Context) : PagerAdapter() {
    override fun isViewFromObject(view: View?, obj: Any?): Boolean = view == obj

    override fun getCount(): Int = 0
}