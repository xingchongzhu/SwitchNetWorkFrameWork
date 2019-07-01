package com.code.refactoring

import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.*
import okhttp3.Response
import java.io.IOException

class OkHttpProcessor : IHttpprocessor{

    private var client : OkHttpClient? = null
    private var mHandler : Handler? = null
    constructor(){
        client = OkHttpClient()
        mHandler = Handler(Looper.getMainLooper())
    }

    override fun post(url: String, params: HashMap<String, Object>, callback: ICallBack) {
       val requstbody : RequestBody = appendBody(params)
       val request: Request? = Request.Builder().url(url).post(requstbody).build()
        client?.newCall(request)?.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback?.onFail()
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful()){
                    val str : String = response.body()!!.string()
                    mHandler?.post(Runnable {
                        callback?.onSuncess(str)
                    })
                }
            }

        })
    }

    private fun appendBody(params: HashMap<String, Object>): RequestBody {
        val body : FormBody.Builder = FormBody.Builder()
        if(params == null || params.isEmpty()){
            return body.build();
        }
        params?.forEach(){
            body.add(it.key,it.value.toString())
        }
        return body.build()
    }




}