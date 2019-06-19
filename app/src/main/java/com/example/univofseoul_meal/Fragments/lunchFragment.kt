package com.example.univofseoul_meal.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.univofseoul_meal.R


class lunchFragment : androidx.fragment.app.Fragment() {

    fun newInstance() : lunchFragment {
        return lunchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_lunch, container, false)
        return view
    }
}
