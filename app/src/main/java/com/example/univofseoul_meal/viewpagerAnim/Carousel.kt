package com.example.univofseoul_meal.viewpagerAnim

import androidx.viewpager.widget.ViewPager
import android.view.View
import android.content.Context
import androidx.core.view.ViewCompat

class Carousel: ViewPager.PageTransformer {

    private var maxTranslateOffsetX: Int = 0
    private var viewPager: ViewPager? = null

    constructor(context: Context){
        this.maxTranslateOffsetX = dp2px(context, 180f)
    }

    override fun transformPage(view: View, position: Float) {
        if (viewPager == null) {
            viewPager = view.getParent() as ViewPager
        }

        if (viewPager == null) {
            viewPager = view.parent as ViewPager
        }

        val leftInScreen = view.left - viewPager!!.getScrollX()
        val centerXInViewPager = leftInScreen + view.measuredWidth / 2
        val offsetX = centerXInViewPager - viewPager!!.getMeasuredWidth() / 2
        val offsetRate = offsetX.toFloat() * 0.3f / viewPager!!.getMeasuredWidth()//옆에 있는 뷰페이저 크기(숫자가 작을수록 뷰 크기가 커짐)
        val scaleFactor = 1 - Math.abs(offsetRate)

        if (scaleFactor > 0) {
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
            view.translationX = -maxTranslateOffsetX * offsetRate
            //ViewCompat.setElevation(view, 0.0f);
        }
        ViewCompat.setElevation(view, scaleFactor)
    }

    private fun dp2px(context: Context, dipValue: Float): Int {
        val m = context.resources.displayMetrics.density
        return (dipValue * m + 0.5f).toInt()
    }
}