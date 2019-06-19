package com.example.univofseoul_meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.univofseoul_meal.Adapter.pagerAdapter_main
import com.example.univofseoul_meal.Model.FragmentMainModel
import com.example.univofseoul_meal.viewpagerAnim.Carousel
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    private val TAG = "TAG_MainActivity"

    //시립대 학생회관 1층 메뉴
//    private val address = "https://www.uos.ac.kr/food/placeList.do?epTicket=LOG"

    internal var fragmentMainList : MutableList<FragmentMainModel> = mutableListOf()
    private val adapter = pagerAdapter_main(this, fragmentMainList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //뷰페이저 아이템 추가
        DummyData()
        //뷰페이저 설정
        initViewPager()

    }

    override fun onStart() {
        super.onStart()
        //앱이 실행되자마자 광고 시작
        adSetting()
    }

    private fun adSetting() {
        //https://devmingsa.tistory.com/17 여기참고하셈

        MobileAds.initialize(this, getString(R.string.admob_app_id))

        val mAdView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //애드몹 설정(옵션임)
        mAdView.adListener = object: AdListener(){
            //광고가 로드되면
            override fun onAdLoaded() {
                super.onAdLoaded()
                Log.d(TAG, "광고가 성공적으로 로드되었습니다.")
            }
            //광고 로드 실패하면
            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                Log.d(TAG, "광고 로드에 실패했습니다.")
            }
            //광고 탭하면
            override fun onAdOpened() {
                super.onAdOpened()
            }

            override fun onAdClicked() {
                super.onAdClicked()
                toast("광고입니다.")
            }
            override fun onAdClosed() {
                super.onAdClosed()
            }
            override fun onAdImpression() {
                super.onAdImpression()
            }
            //사용자 클릭으로 인해 현재 앱이 배경인 다른 앱(예 : 구글 플레이)를 열 때인 onAdOpened이후 실행
            override fun onAdLeftApplication() {
                super.onAdLeftApplication()
            }
        }
    }

    private fun DummyData() {
        fragmentMainList.add(FragmentMainModel("학관식당 1층", R.drawable.image1))
        fragmentMainList.add(FragmentMainModel("학관식당 2층 아느칸", R.drawable.image2))
        fragmentMainList.add(FragmentMainModel("자연과학관 1층", R.drawable.image3))
        fragmentMainList.add(FragmentMainModel("본관 8층", R.drawable.image4))
        fragmentMainList.add(FragmentMainModel("생활관", R.drawable.image5))
    }

    private fun initViewPager() {
        place_viewpager_mainActivity.adapter = adapter
        place_viewpager_mainActivity.offscreenPageLimit = 3
        place_viewpager_mainActivity.clipChildren = false
        place_viewpager_mainActivity.clipToPadding = false
        place_viewpager_mainActivity.setPadding(40, 0, 40, 0)
        place_viewpager_mainActivity.pageMargin = -300
        place_viewpager_mainActivity.overScrollMode = 2
        place_viewpager_mainActivity.setPageTransformer(true, Carousel(this))
        viewpagerIndicator_mainActivity.attachToPager(place_viewpager_mainActivity)
    }

}
