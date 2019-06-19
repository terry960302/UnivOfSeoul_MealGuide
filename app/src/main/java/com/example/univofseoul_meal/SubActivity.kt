package com.example.univofseoul_meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.univofseoul_meal.Adapter.TabViewpagerAdapter
import com.example.univofseoul_meal.Utils.WebCrawlingUtil
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        //MainActivity에서 건너온 건물이름과 건물에 해당하는 학식 주소이름
        val intent = this.intent
        val buildingName = intent.getStringExtra("buildingName").toString()
        textview_subActivity.text = buildingName

        val address = intent.getStringExtra("address").toString()
        val link = WebCrawlingUtil.getLink(address)

        initTabViewpager(link)

    }

    private fun initTabViewpager(link : String) {
        val adapter = TabViewpagerAdapter(supportFragmentManager, link)
        viewpager_subActivity.adapter = adapter
        tabLayout_subActivity.setupWithViewPager(viewpager_subActivity)
        tabLayout_subActivity.getTabAt(0)?.setIcon(R.drawable.ic_rice)
        tabLayout_subActivity.getTabAt(1)?.setIcon(R.drawable.ic_lunch)
        tabLayout_subActivity.getTabAt(2)?.setIcon(R.drawable.ic_dinner)
    }
}
