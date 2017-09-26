package com.example.yoshidamakoto.constraintlayoutpractice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by yoshidamakoto on 2017/09/27.
 */

class AllContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.all_content_fragment, container, false)
    }
}