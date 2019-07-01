package com.code.refactoring

import android.util.Log
import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class HttpCallBack<T> : ICallBack{

     override fun onSuncess(result: String) {
         Log.d("HttpCallBack","result = "+result)
         val gson : Gson = Gson()
         val clazz : Class<*> = getT(this as Object)
         val t : T = gson.fromJson(result,clazz) as T

         onSucess(t)
     }

     fun getT(obj : Object):Class<*>{
         val genType : Type = obj.`class`.genericSuperclass
         val actualTypeArguments : Array<Type> = (genType as ParameterizedType).actualTypeArguments
         return actualTypeArguments[0] as Class<*>
     }

     abstract fun onSucess(t:T)

     override fun onFail() {
        Log.d("HttpCallBack","fail");
     }

 }