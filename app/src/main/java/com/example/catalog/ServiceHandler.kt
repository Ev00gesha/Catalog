package com.example.catalog

import android.content.Context
import com.google.gson.Gson

class ServiceHandler(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("servicesPref", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()

    fun addData(title: String, price: String, url:String, img:String){
        val existingData: MutableList<String> = getDataArray().toMutableList()

        val newData = ServiceItem(title, price, url, img)

        val newDataString = gson.toJson(newData)
        existingData.add(newDataString)

        editor.putStringSet("services", existingData.toSet())
        editor.apply()
    }

    fun addUrl(url: String){
        editor.putString("url", url)
        editor.apply()
    }

    fun getUrl():String{
        var url = sharedPreferences.getString("url", "https://www.youtube.com/watch?v=0vgnaXn0zAI").toString()
        if(url == "")
            url = "https://www.youtube.com/watch?v=0vgnaXn0zAI"
        return url
    }

    fun getDataArray(): Set<String> {
        return sharedPreferences.getStringSet("services", mutableSetOf()) ?: mutableSetOf()
    }

    fun clearAllData(){
        editor.clear()
        editor.apply()
    }

}
data class ServiceItem(val title: String, val price: String, val url: String, val img: String)