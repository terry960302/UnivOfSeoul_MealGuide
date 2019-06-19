package com.example.univofseoul_meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.univofseoul_meal.Model.FragmentSubModel
import com.example.univofseoul_meal.Model.recyclerviewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_sub.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class SubActivity : AppCompatActivity() {
    //학관 1층 링크
    private val address1 = "https://www.uos.ac.kr/food/placeList.do?epTicket=LOG"
    //학관 2층 링크
    private val address2 = "https://www.uos.ac.kr/food/placeList.do?rstcde=030&epTicket=LOG"
    //자연과학관 링크
    private val address3 = "https://www.uos.ac.kr/food/placeList.do?rstcde=040&epTicket=LOG"
    //본관8층
    private val address4 = "https://www.uos.ac.kr/food/placeList.do?rstcde=010&epTicket=LOG"
    //생활관
    private val address5 = "https://www.uos.ac.kr/food/placeList.do?rstcde=050&epTicket=LOG"

    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        initRecyclerview()

        getMenuMap(address1)

    }

    private fun initRecyclerview() {
        recyclerview_subActivity.adapter = adapter
        recyclerview_subActivity.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }


    private fun getMenuMap(address : String) {

        doAsync {
            val document = Jsoup.connect(address).get()

            //조식, 중식, 석식 메뉴 모두 가져옴.
            val menu = document.select("div#day").select("td.al")

            var resultMap = mutableMapOf<String, String>()

            resultMap["조식"] = makeLineText(menu[0])
            resultMap["중식"] = makeLineText(menu[1])
            resultMap["석식"] = makeLineText(menu[2])

            //즉각적으로 ui에 띄워주려면 이 스레드 사용해야함.
            uiThread {
                adapter.add(reyclerview(recyclerviewModel("조식", resultMap["조식"])))
                adapter.add(reyclerview(recyclerviewModel("중식", resultMap["중식"])))
                adapter.add(reyclerview(recyclerviewModel("석식", resultMap["석식"])))
            }
        }
    }
    //html을 가지고 태그없애고 줄바꿈있는 텍스트로 만듬
    fun makeLineText(element : Element) : String{

        val list = element.html().split("<br>").toTypedArray()

        var result = ""
        list.forEach {
            if(it == " ") return@forEach
            result+=it+"\n"
        }
        return result
    }
}
