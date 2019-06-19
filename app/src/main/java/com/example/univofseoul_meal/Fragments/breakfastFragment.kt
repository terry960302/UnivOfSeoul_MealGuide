package com.example.univofseoul_meal.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.univofseoul_meal.R
import com.example.univofseoul_meal.Utils.WebCrawlingUtil
import kotlinx.android.synthetic.main.fragment_breakfast.view.*


class breakfastFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_breakfast, container, false)

        //MainActivity에서 받은 string -> 함수로 링크 뽑아내기 -> 그 링크로 웹크롤링 정보가져오기
        val address = arguments?.getString("address").toString()

        WebCrawlingUtil.getMenuMap(title = "조식",
            textview = view.textview_breakfastFrag,
            address = address)

        return view
    }
}
