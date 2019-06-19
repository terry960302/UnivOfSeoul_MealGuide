package com.example.univofseoul_meal.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.univofseoul_meal.R
import com.example.univofseoul_meal.Utils.WebCrawlingUtil
import kotlinx.android.synthetic.main.fragment_lunch.view.*


class lunchFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_lunch, container, false)

        //MainActivity에서 받은 string -> 함수로 링크 뽑아내기 -> 그 링크로 웹크롤링 정보가져오기
        val address = arguments?.getString("address").toString()

        WebCrawlingUtil.getMenuMap(title = "중식",
            textview = view.textview_lunchFrag,
            address = address)

        return view
    }
}
