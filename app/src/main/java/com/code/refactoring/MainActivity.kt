package com.code.refactoring

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {
    val TAG:String = "MainActivity"
    val url: String = "http://v.juhe.cn/historyWeather/citys"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // HttpHelper.
        val params: HashMap<String, Object> = HashMap()
        params.put("province_id", 1 as Object)
        HttpHelper.instance().post(
                url,
                params,
                object : HttpCallBack<Response>() {

                    override fun onSucess(response: Response) {
                        Log.d(TAG,response.toString())
                    }
                }
        )
    }
}