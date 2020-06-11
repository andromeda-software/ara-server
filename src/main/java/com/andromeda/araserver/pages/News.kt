package com.andromeda.araserver.pages

import com.andromeda.araserver.util.NewsData
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
/** This class handles the ara news feed**/
class News{
    init {
        getData()
        val time = Timer()
        val refresh = object : TimerTask() {
            override fun run() {
                println("refreshing at " + System.currentTimeMillis())
                getData()
            }
        }
        time.schedule(refresh, 0, 1800000)
    }

    fun getData() {
        NewsCache.us = getNews("https://newsapi.org/v2/top-headlines?country=us&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1")
	NewsCache.moneyEn = getNews("https://newsapi.org/v2/top-headlines?category=business&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1")
	NewsCache.techEn = getNews("https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1")
        NewsCache.me = getNews("https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1")
        NewsCache.uk = getNews("https://newsapi.org/v2/top-headlines?country=gb&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1")
        NewsCache.de = getNews("https://newsapi.org/v2/top-headlines?country=de&apiKey=4e3c5ce2cfff4634b4c4811c01fd2ad1")

    }
    fun getNews(link:String): ArrayList<NewsData> {
        val newsData = arrayListOf<NewsData>()
        val data = URL(link).readText()
        JsonParser().parse(data).asJsonObject.get("articles").asJsonArray.forEach{
            val json = it.asJsonObject
            try{
                newsData.add(NewsData(json.get("title").asString, json.get("content").asString, json.get("description").asString, json.get("url").asString,json.get("urlToImage").asString))
            }
            catch (e:Exception){}        }
        return newsData
    }

}
