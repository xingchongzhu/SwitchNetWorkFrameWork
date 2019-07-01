package com.code.refactoring

interface IHttpprocessor{
    fun post(url : String,params : HashMap<String,Object>,callback : ICallBack)
}