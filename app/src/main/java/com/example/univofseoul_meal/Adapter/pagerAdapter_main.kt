package com.example.univofseoul_meal.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.example.univofseoul_meal.Models.FragmentMainModel
import com.example.univofseoul_meal.R
import com.example.univofseoul_meal.SubActivity
import kotlinx.android.synthetic.main.page_main.view.*

class pagerAdapter_main(internal var context : Context,
                        internal var fragmentMainList : MutableList<FragmentMainModel>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.page_main, container, false)

        val placeName = view.placeName_textview_page
        val placeImage = view.background_imageview_page

        placeName.text = fragmentMainList[position].placeName
        placeImage.setImageResource(fragmentMainList[position].placeImage)

        view.setOnClickListener {
            val intent = Intent(context, SubActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity, placeName,ViewCompat.getTransitionName(placeName)!!)
            intent.putExtra("address", "address${position}")
            intent.putExtra("buildingName", placeName.text.toString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                context.startActivity(intent, options.toBundle())
            }
        }

        container.addView(view)
        return view
    }
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return fragmentMainList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}