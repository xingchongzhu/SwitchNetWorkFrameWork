package com.code.refactoring

import android.util.Log
import java.lang.StringBuilder
import java.net.URLEncoder

class HttpHelper : IHttpprocessor{

    var mIHttpprocessor : IHttpprocessor? = null
    companion object {
        private var instance : HttpHelper? = null
        get(){
            if(field == null){
                field = HttpHelper();
            }
            return field;
        }

        @Synchronized
        fun instance():HttpHelper{
            return instance!!
        }
    }

    fun init(httpPrecoss:IHttpprocessor){
        mIHttpprocessor = httpPrecoss
    }

    override fun post(url: String, params: HashMap<String, Object>, callback: ICallBack) {
        var finalUrl:String  = appendParams(url,params)
        mIHttpprocessor?.post(finalUrl,params,callback)
    }

    private fun appendParams(url: String, params: HashMap<String, Object>):String {
        if(params == null || params.isEmpty()){
            return url
        }
        val stringBuild:StringBuilder = StringBuilder(url)
        if(stringBuild.indexOf("?") <= 0){
            stringBuild.append("?")
        }else{
            if(!stringBuild.toString().endsWith("?")){
                stringBuild.append("&")
            }
        }

        params.forEach {
            stringBuild.append("&"+it.key).append("=").append(encode(it.value.toString()))
        }
        Log.d("HttpHelper","url = " +stringBuild.toString())
        return stringBuild.toString()
    }

    private fun encode(str:String):String{
        return URLEncoder.encode(str,"utf-8")
    }

}