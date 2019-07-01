package com.code.refactoring

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class VolleyPorcess : IHttpprocessor {

    companion object {
        var mQueue:RequestQueue ? = null
    }

    constructor(context:Context){
        mQueue = Volley.newRequestQueue(context)
    }

    override fun post(url: String, params: HashMap<String, Object>, callback: ICallBack) {
        val stringRequest:StringRequest = StringRequest(Request.Method.POST,url,Response.Listener<String> {
            callback.onSuncess(it)
        },Response.ErrorListener{
            Log.d("VolleyPorcess",it.toString())
            callback.onFail()
        })
        mQueue?.add(stringRequest)
    }
}