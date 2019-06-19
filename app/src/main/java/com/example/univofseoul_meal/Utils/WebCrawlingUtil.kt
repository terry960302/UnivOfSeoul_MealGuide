package com.example.univofseoul_meal.Utils

import android.text.TextUtils
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object WebCrawlingUtil {

    fun getMenuMap(title : String,
                   textview : TextView,
                   address : String) {

        doAsync {
            val document = Jsoup.connect(address).get()

            //조식, 중식, 석식 메뉴 모두 가져옴.
            val menu = document.select("div#day").select("td.al")

            var resultMap = mutableMapOf<String, String>()

            resultMap["조식"] = makeLineText(menu[0])//조식메뉴
            resultMap["중식"] = makeLineText(menu[1])//중식 메뉴
            resultMap["석식"] = makeLineText(menu[2])//석식 메뉴

            println(resultMap.toString())

            //즉각적으로 ui에 띄워주려면 이 스레드 사용해야함.(화면에 띄우는 건 여기에)
            uiThread {
                if(textview.text.length < 10){
                    textview.text = "이 시간은 운영하지 않습니다."
                }
                else{
                    textview.text = resultMap[title]
                }
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
    //MainActivity에서 건물을 누르면 그에 맞는 string 액티비티에서 보내주는데
    //받은 string을 이 함수에 넣어서 링크를 산출해내면 된다.
    fun getLink(address : String) : String{

        //학관 1층
        val address0 = "https://www.uos.ac.kr/food/placeList.do?epTicket=LOG"
        //학관 2층
        val address1 = "https://www.uos.ac.kr/food/placeList.do?rstcde=030&epTicket=LOG"
        //자연과학관
        val address2 = "https://www.uos.ac.kr/food/placeList.do?rstcde=040&epTicket=LOG"
        //본관8층
        val address3 = "https://www.uos.ac.kr/food/placeList.do?rstcde=010&epTicket=LOG"
        //생활관
        val address4 = "https://www.uos.ac.kr/food/placeList.do?rstcde=050&epTicket=LOG"

        val addressMap = mutableMapOf<String, String>()

        addressMap["address0"] = address0
        addressMap["address1"] = address1
        addressMap["address2"] = address2
        addressMap["address3"] = address3
        addressMap["address4"] = address4

        return addressMap[address]!!//결과물로 링크가 반환된다.
    }
}