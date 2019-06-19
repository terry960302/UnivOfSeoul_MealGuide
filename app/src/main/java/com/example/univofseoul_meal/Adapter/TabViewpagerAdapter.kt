package com.example.univofseoul_meal.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.univofseoul_meal.Fragments.breakfastFragment
import com.example.univofseoul_meal.Fragments.dinnerFragment
import com.example.univofseoul_meal.Fragments.lunchFragment

class TabViewpagerAdapter(fm : FragmentManager,
                          val address : String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                val frag = breakfastFragment()
                val bundle = Bundle()
                bundle.putString("address", address)
                frag.arguments = bundle
                return frag
            }
            1 -> {
                val frag = lunchFragment()
                val bundle = Bundle()
                bundle.putString("address", address)
                frag.arguments = bundle
                return frag
            }
            else -> {
                val frag = dinnerFragment()
                val bundle = Bundle()
                bundle.putString("address", address)
                frag.arguments = bundle
                return frag
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "조식"
            1 -> "중식"
            else -> "석식"
        }
    }
}